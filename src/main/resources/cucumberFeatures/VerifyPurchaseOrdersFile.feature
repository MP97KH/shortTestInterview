Feature: verify the purchase order file

  Scenario: verify the purchase order file content
    Given the file with the name 'purchase_orders' and '.xls' extension exists
    When the file with the name 'purchase_orders' and '.xls' extension is readable
    Then the file 'purchase_orders.xls' verification can be started
    And the count of columns in the file equals 4
    And the columns are named: 'Buyer', 'Buyer', 'Buyer External ID', 'Sales Order'
    And there is more than 10 lines in the file