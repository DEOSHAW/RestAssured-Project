@req
Feature: ReqRes
@createUser @modifyUser
Scenario: Create User
Given request is made to ReqRes API over http
When the request is successful
Then user id is stored and displayed


@modifyUser
Scenario: Modify User
Given request is made to ReqRes Patch API over http
When the patch request is successful
Then created date is displayed