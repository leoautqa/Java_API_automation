@regression @regularAccount
Feature: Regular Account

	Background: 
    Given I am logged in as regular

  @tcRa01
  Scenario: 01 Tab Home
    Given   On the Home tab
    When    Search for a product that does not exist
    And     Search
    Then    Show no results

	@tcRa02
	Scenario: 02 Detail product
		Given     On the Home tab
    When      Search for a product
    And       Detail the product
    Then      Product details must be visible
    
	@tcRa03
	Scenario: 03: Clear shopping list
    Given     On the Home tab
    When      Search for a product
    And       Click in Adicionar a lista
    When      Click in Limpar Lista
    Then      Message Seu carrinho está vazio must be visible
  
  @tcRa04
  Scenario: 04: Shopping cart
    Given     On the Home tab
    When      Search for a product
    And       Click in Adicionar a lista
    When      Click in Adicionar no carrinho
    Then      The product must be in the shopping cart