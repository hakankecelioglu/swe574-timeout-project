package demo;

import helpers.ServiceHelper;
import helpers.ValidationHelper;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import repository.UserRepository;
import common.BusinessException;
import common.DBUtility;
import common.ErrorMessages;
import common.ResponseHeader;
import dto.NewsFeedDTO;
import entity.Role;
import entity.User;
import entity.UserBasicInfo;
import entity.UserCommInfo;
import entity.UserExtraInfo;

@RestController
public class UserRestServices {

	//register function
	@RequestMapping(value = "/register")
	public @ResponseBody Object registerUser(
			@RequestParam(value = "userEmail") String userEmail,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "lastName") String lastName,
			@RequestParam(value = "role") String role,
			HttpServletResponse resp) {
		
		EntityManager em = ServiceHelper.initialize(resp);
		User user = null;
		try {

			ValidationHelper.validateEmail(userEmail);
			ValidationHelper.validatePassword(password);
			
			UserRepository ur = new UserRepository(em);
			
			if (ur.getUserByUserNumberEmail(userEmail) > 0){
				ServiceHelper.businessError(ErrorMessages.userAlreadyExistCode,
						ErrorMessages.userAlreadyExist);
			}

			user = new User(userEmail, password);
			user.setUserBasicInfo(new UserBasicInfo());
			user.getUserBasicInfo().setUser(user);
			user.setUserCommInfo(new UserCommInfo());
			user.getUserCommInfo().setUser(user);
			user.setUserExtraInfo(new UserExtraInfo());
			user.getUserExtraInfo().setUser(user);

			Role roleObj = ur.getRoleByName(role);
			if (roleObj == null) {
				roleObj = new Role(role);
				ur.insertRole(roleObj);
			}
			user.setRole(roleObj);

			user.getUserBasicInfo().setFirstName(firstName);
			user.getUserBasicInfo().setLastName(lastName);

			ur.insertUser(user);

			DBUtility.commitTransaction(em);
		} catch (BusinessException e) {
			DBUtility.rollbackTransaction(em);
			return new ResponseHeader(false, e.getCode(), e.getMessage());
		} catch (Exception e) {
			DBUtility.rollbackTransaction(em);
			return new ResponseHeader(false, e.getMessage());
		}
		return new ResponseHeader();
	}
	
	@RequestMapping(value = "/email/isAvailable")
	public @ResponseBody Object emailIsAvailable(
			@RequestParam(value = "userEmail") String userEmail,
			HttpServletResponse resp) {
		
		EntityManager em = ServiceHelper.initialize(resp);

		try {

			ValidationHelper.validateEmail(userEmail);
			
			UserRepository ur = new UserRepository(em);

			if (ur.getUserByUserNumberEmail(userEmail) > 0){
				ServiceHelper.businessError(ErrorMessages.emailNotAvailableCode, ErrorMessages.emailNotAvailable);
			}

			DBUtility.commitTransaction(em);
		} catch (BusinessException e) {
			DBUtility.rollbackTransaction(em);
			return new ResponseHeader(false, e.getCode(), e.getMessage());
		} catch (Exception e) {
			DBUtility.rollbackTransaction(em);
			return new ResponseHeader(false, e.getMessage());
		}
		return new ResponseHeader();
	}

	//edit user function
	@RequestMapping(value = "/profile/edit")
	public @ResponseBody Object editProfile(
			@RequestParam(value = "sessionId") String sessionId,
			@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName,
			@RequestParam(value = "Gsm", required = false) Long Gsm,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "birthdate", required = false) String birthdateString,
			@RequestParam(value = "about", required = false) String about,
			@RequestParam(value = "interests", required = false) String interests,
			@RequestParam(value = "gender", required = false) String gender,
			@RequestParam(value = "languages", required = false) String languages,
			HttpServletResponse resp) {

		EntityManager em = ServiceHelper.initialize(resp);

		try {
			User user = ServiceHelper.getSessionUser(em, sessionId);
			
			if (user.getUserBasicInfo() == null) {
				user.setUserBasicInfo(new UserBasicInfo());
				user.getUserBasicInfo().setUser(user);
			}

			if (user.getUserCommInfo() == null) {
				user.setUserCommInfo(new UserCommInfo());
				user.getUserCommInfo().setUser(user);
			}

			if (user.getUserExtraInfo() == null) {
				user.setUserExtraInfo(new UserExtraInfo());
				user.getUserExtraInfo().setUser(user);
			}
			// user.getUserExtraInfo().setUserId(user.getUserId());
			// if (user.getRole() == null){
			// user.setRole(new Role());
			// user.getRole().getUsers().add(user);
			// }
			if (!ValidationHelper.isNullOrWhitespace(firstName))
				user.getUserBasicInfo().setFirstName(firstName);
			if (!ValidationHelper.isNullOrWhitespace(lastName))
				user.getUserBasicInfo().setLastName(lastName);
			if (Gsm != null && Gsm > 0)
				user.getUserCommInfo().setMobilePhone(Gsm);
			if (!ValidationHelper.isNullOrWhitespace(address))
				user.getUserCommInfo().setAddress(address);
			if (!ValidationHelper.isNullOrWhitespace(birthdateString)){
				try {
			        Date birthdate = ServiceHelper.dateParser(birthdateString);
			        user.getUserExtraInfo().setBirthDate(birthdate);
			    } catch (BusinessException e) {
			        //do nothing
			    }
			}
			if (!ValidationHelper.isNullOrWhitespace(about))
				user.getUserExtraInfo().setAbout(about);
			if (!ValidationHelper.isNullOrWhitespace(interests))
				user.getUserExtraInfo().setInterests(interests);
			if (!ValidationHelper.isNullOrWhitespace(gender))
				user.getUserBasicInfo().setGender(gender);
			if (!ValidationHelper.isNullOrWhitespace(languages))
				user.getUserExtraInfo().setLanguages(languages);
			
			UserRepository ur = new UserRepository(em);
			ur.insertUser(user);
			
			DBUtility.commitTransaction(em);
		}catch (BusinessException e) {
			DBUtility.rollbackTransaction(em);
			return new ResponseHeader(false, e.getCode(), e.getMessage());
		}catch (Exception e) {
			DBUtility.rollbackTransaction(em);
			return new ResponseHeader(false, e.getMessage());
		}
		return new ResponseHeader();
	}

	//get session user profile
	@RequestMapping(value = "/profile/get")
	public @ResponseBody Object getProfile(
			@RequestParam(value = "sessionId") String sessionId,
			HttpServletResponse resp) {

		EntityManager em = ServiceHelper.initialize(resp);

		User user;
		try {
			user = ServiceHelper.getSessionUser(em, sessionId);
			
			DBUtility.commitTransaction(em);
		}catch (BusinessException e) {
			DBUtility.rollbackTransaction(em);
			return new ResponseHeader(false, e.getCode(), e.getMessage());
		}catch (Exception e) {
			DBUtility.rollbackTransaction(em);
			return new ResponseHeader(false, e.getMessage());
		}
		return user;
	}
	
	//get any user profile by id
	@RequestMapping(value = "/profile/getById")
	public @ResponseBody Object getProfileByUserId(
			@RequestParam(value = "sessionId") String sessionId,
			@RequestParam(value = "userId") Long userId,
			HttpServletResponse resp) {

		EntityManager em = ServiceHelper.initialize(resp);

		User user;
		User result;
		try {
			user = ServiceHelper.getSessionUser(em, sessionId);
			UserRepository ur = new UserRepository(em);
			result = ur.getUserById(userId);
			
			DBUtility.commitTransaction(em);
		}catch (BusinessException e) {
			DBUtility.rollbackTransaction(em);
			return new ResponseHeader(false, e.getCode(), e.getMessage());
		}catch (Exception e) {
			DBUtility.rollbackTransaction(em);
			return new ResponseHeader(false, e.getMessage());
		}
		return result;
	}
	
	//get any user profile by id
		@RequestMapping(value = "/profile/getByEmail")
		public @ResponseBody Object getProfileByUserEmail(
				@RequestParam(value = "sessionId") String sessionId,
				@RequestParam(value = "userEmail") String userEmail,
				HttpServletResponse resp) {

			EntityManager em = ServiceHelper.initialize(resp);

			User user;
			User result;
			try {
				user = ServiceHelper.getSessionUser(em, sessionId);
				UserRepository ur = new UserRepository(em);
				result = ur.getUserByEmail(userEmail);
				
				DBUtility.commitTransaction(em);
			}catch (BusinessException e) {
				DBUtility.rollbackTransaction(em);
				return new ResponseHeader(false, e.getCode(), e.getMessage());
			}catch (Exception e) {
				DBUtility.rollbackTransaction(em);
				return new ResponseHeader(false, e.getMessage());
			}
			return result;
		}
	
	//login function
	@RequestMapping(value = "/login")
    public
    @ResponseBody
    ResponseHeader login(
            @RequestParam(value = "userEmail") String userEmail,
            @RequestParam(value = "password") String password, HttpServletResponse resp) {

		EntityManager em = ServiceHelper.initialize(resp);
        
        ResponseHeader rh;
        
        try {
			UserRepository ur = new UserRepository(em);
			
			rh = ur.login(userEmail, password);
			
			DBUtility.commitTransaction(em);
		}catch (BusinessException e) {
			DBUtility.rollbackTransaction(em);
			return new ResponseHeader(false, e.getCode(), e.getMessage());
		}catch (Exception e) {
			DBUtility.rollbackTransaction(em);
			return new ResponseHeader(false, e.getMessage());
		}
		return rh;
    }
	
	// news feed function
	@RequestMapping(value = "/newsFeed")
	public @ResponseBody Object getNewsFeed(
			@RequestParam(value = "sessionId") String sessionId,
			HttpServletResponse resp) {

		EntityManager em = ServiceHelper.initialize(resp);

		User user;
		List<NewsFeedDTO> feeds = null;
		try {

			user = ServiceHelper.getSessionUser(em, sessionId);
			UserRepository ur = new UserRepository(em);
			feeds = ur.getNewsFeed(user);

			DBUtility.commitTransaction(em);
		} catch (BusinessException e) {
			DBUtility.rollbackTransaction(em);
			return new ResponseHeader(false, e.getCode(), e.getMessage());
		} catch (Exception e) {
			DBUtility.rollbackTransaction(em);
			return new ResponseHeader(false, e.getMessage());
		}
		return feeds;
	}
}
