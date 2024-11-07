@regression @usuarios
Feature: Administrator Account

  Background: 
    Given Host

	@usu01
  Scenario: 01 Tab home
    When      Get list users
    Then      The status code must be 200
    
