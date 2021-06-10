Feature: GitHub
@github
Scenario: Get the list of events
Given Get request is made to Github API over http
When the list of events is extracted
Then the response body for "type" contains "PushEvent"