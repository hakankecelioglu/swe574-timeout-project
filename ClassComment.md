# Class Comment #

### Method Add Comment ###

Creates a new comment with current time and new id.

> - Input Parameters
    * Top Comment Id
    * Custom Type Id
    * Title
    * Text

> - Output Parameter
    * A Comment Object

### Method Edit Comment ###

Edits given comment with given title and text, also updates time column.

> - Input Parameters
    * Comment Id
    * Title
    * Text

> - Output Parameter
    * Edited Comment Object

### Method Delete Comment ###

Deletes related comment object from db. It returns a boolean variable to say, it is done successfully or not.

> - Input Parameters
    * Comment Id

> - Output Parameter
    * A Boolean Variable

### Method Get All Comments For Custom Type ###

Gives a list of comments related to given custom type id.

> - Input Parameters
    * Custom Type Id

> - Output Parameter
    * List of Comment Objects

### Method Get User Action History ###

Gives all comments which is created by given user.

> - Input Parameters
    * User Id

> - Output Parameter
    * List of Comment Objects

### Method Get All Comments For Post ###

Gives all comments related to given post.

> - Input Parameters
    * Post Id

> - Output Parameter
    * List Of Comment Objects