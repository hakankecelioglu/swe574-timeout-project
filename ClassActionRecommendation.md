# Class Action Recommendation #

### Method Find Recommended Actions ###

this method is used to recommend actions to users. It receives an argument of type user and returns an array of actions.

> - Input Parameters
    * user : User

> - Output Parameter
    * Action[.md](.md)

### Method Insert Action Recommendation ###

this method inserts results of the method above into users page.

> - Input Parameters
    * mainUser : user
    * recommendedActions : action[.md](.md)


### Method Get Action Recommendations ###

by this method we can get the suggested actions by system for us.

> - Input Parameters
    * user : User

> - Output Parameter
    * ActionsRecommendation[.md](.md)