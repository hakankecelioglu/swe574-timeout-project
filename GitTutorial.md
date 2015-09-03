# What is Git and How To Install? #

**Git** is a widely known revision control system. For our new project, we will be using that to keep our sources in a repository and managing changes of the project which are done by project members.

To install git on Windows:
  * Download git-version.exe to install from http://git-scm.com/download/win,
  * Open the downloaded file and follow instructions (Click next, next and next),
  * Choose Git Bash option to use git through a git special command line application,
  * After that, you will have git on your system. You should open Git Bash file to use git.

_Note: For video tutorial to install git on Windows, you can watch http://youtu.be/cEGIFZDyszA._

To install git on Mac:
  * Download git-version.dmg to install from http://git-scm.com/download/mac,
  * Open the downloaded file and follow instructions to install git on your system,
  * After that, you can use git through terminal window.

_Note: For video tutorial to install git on Mac, you can watch http://youtu.be/LK0vMt_lEbQ._

# How to get our project repository #
  * Open terminal window on Mac OSX or Git Bash on Windows and go to the directory you want to get the repository inside,
  * Write _git clone https://aliveli@code.google.com/p/swe574-timeout-project/_ and press enter,
  * With this command, repository will be taken from the repository and copied to your local repository,
  * Check your current file to see if repository is taken.

# How to introduce yourself to git #
  * You should check your code.google.com password via this link https://code.google.com/hosting/settings,
  * Then you should configure git to know you with two commands shown below;
    1. _git config --global user.name "Sam Smith"_
    1. _git config --global user.email aliveli@gmail.com (your mail account shown at the link above)_

# How to commit your changes #
> I have already create a branch called master and we all will use that branch for this project. Our project is not a very long term project, so, also tagging is not necessary to discuss here. Large scale projects which have so many versions will use branches based on versions and use tagging as a return point in order to use when disaster happens. So, it is just an information for you to remember when somebody asks or you encounters.

  * Firstly, if you add a new file to the project repository, you should add it by using _git add /images/4.png_, you may also use _git add `*`_ in order to make git consider those files,
  * Secondly, you should use _git commit -m "Explanation"_, to commit your changes in your local repository, alternatively you may use git commit -a and then enter the explanation to explain what you changed and what other users should understand from the change. Additionally, it is good practice to provide the related issue title in the explanation,
  * Finally, you should use _git push origin master_, to commit your changes into remote repository.

# How to get other users' changes #
> It is a team project and every participant of the team should commit their changes to the repository. After implementing the code solves an issue, it should be committed before passing to another issue. It is good practice committing changes often but committed changes should not include any errors (compilation errors...). With _git pull_, you can get changes done by participants.

# Revert your changes and go on with the last version on remote repository #
> When you messed up and wanted to get original file from the repository, use _git checkout --/images/4.png_. To revert all changes in all files, use _git fetch origin_ or _git reset --hard origin/master_.


# Useful Links #
  * Basic Git Commands, https://confluence.atlassian.com/display/STASH/Basic+Git+commands,
  * Git Commands, https://www.siteground.com/tutorials/git/commands.htm,
  * Git Reference, http://git-scm.com/docs

The commands is as follows:

```
Sezgis-MacBook-Pro:~ sezgi$ git clone https://sezgi.seret@code.google.com/p/swe574-timeout-project/
Cloning into 'swe574-timeout-project'...
remote: Counting objects: 3, done.
Unpacking objects: 100% (3/3), done.
Checking connectivity... done.
Sezgis-MacBook-Pro:~ sezgi$ git config --global user.name "Sezgi Seret"
Sezgis-MacBook-Pro:~ sezgi$ git config --global user.email sezgi.seret@gmail.com
Sezgis-MacBook-Pro:~ sezgi$ ls -lrt
total 40
drwxr-xr-x+  5 sezgi  staff   170 Jan  1  2013 Public
drwx------+  6 sezgi  staff   204 Jul 26  2014 Music
drwxr-xr-x   4 sezgi  staff   136 Aug  9  2014 Samsung
drwx------   3 sezgi  staff   102 Aug  9  2014 Applications
drwxrwxrwx   4 sezgi  staff   136 Oct  7 16:35 Adlm
-rw-r--r--   1 sezgi  staff  7898 Oct 22 03:26 java0.log
-rw-r--r--   1 sezgi  staff  9010 Oct 22 03:26 ganttproject.log
drwxr-xr-x   6 sezgi  staff   204 Nov 25 21:49 Projects
drwx------+  6 sezgi  staff   204 Dec 26 20:39 Movies
drwx------@ 58 sezgi  staff  1972 Dec 26 21:50 Library
drwx------+  7 sezgi  staff   238 Jan 13 22:08 Pictures
drwx------+ 12 sezgi  staff   408 Jan 30 19:17 Downloads
drwx------+ 16 sezgi  staff   544 Feb 17 02:55 Documents
drwx------+ 12 sezgi  staff   408 Feb 18 23:21 Desktop
drwxr-xr-x   4 sezgi  staff   136 Feb 18 23:55 swe574-timeout-project
Sezgis-MacBook-Pro:~ sezgi$ cd swe574-timeout-project/
Sezgis-MacBook-Pro:swe574-timeout-project sezgi$ ls -lrt
total 8
-rw-r--r--  1 sezgi  staff  7 Feb 18 23:55 swe574.gitignore
Sezgis-MacBook-Pro:swe574-timeout-project sezgi$ vi sezgideneme.txt
Sezgis-MacBook-Pro:swe574-timeout-project sezgi$ git add *
Sezgis-MacBook-Pro:swe574-timeout-project sezgi$ git commit -m "sezgi first commit"
[master 562d3ac] sezgi first commit
 1 file changed, 1 insertion(+)
 create mode 100644 sezgideneme.txt
Sezgis-MacBook-Pro:swe574-timeout-project sezgi$ git push origin master
Password for 'https://sezgi.seret@code.google.com': 
Counting objects: 4, done.
Delta compression using up to 8 threads.
Compressing objects: 100% (2/2), done.
Writing objects: 100% (3/3), 311 bytes | 0 bytes/s, done.
Total 3 (delta 0), reused 0 (delta 0)
remote: Storing objects: 100% (3/3), done.
remote: Processing commits: 100% (1/1), done.
To https://sezgi.seret@code.google.com/p/swe574-timeout-project/
   fd00c55..562d3ac  master -> master
Sezgis-MacBook-Pro:swe574-timeout-project sezgi$
```

# FAQ #
1. Can I push en empty directory to repo ?

No, you can not. At least one single file should be stored in the directory in order to push.