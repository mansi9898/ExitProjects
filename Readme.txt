Flipkart Automation Test Suite:-

This project is an automated test suite for the Flipkart website (https://www.flipkart.com/) using Selenium and Java. The suite includes 20 automated test cases across various flows and screens, implementing the Page Object Model (POM) with Page Factory, and utilizes TestNG for test management and execution.

Project Overview:- This project aims to automate the testing of Flipkart's website to ensure various functionalities work correctly. The automated test suite covers multiple flows and screens and provides detailed reports on test execution.

Installation:-
1)Clone the repository.
2)Install dependencies:Ensure you have Maven installed. Navigate to the project directory and run:
                       mvn clean install
3)Set up the configuration:-Open Utilities/config.properties and set the necessary configurations.
                            Ensure the ExcelFile/FlipkartReadDataExcel.xlsx is updated with the appropriate test data.
                            
Configuration:-Global Parameters:
               Configure global parameters in Utilities/config.properties.

               Test Data:
               Test data is read from the ExcelFile/FlipkartReadDataExcel.xlsx. You can selectively run test cases by marking     
               'yes' in the "Execution Required" field.

               Logging:
               Configure logging in src/main/resources/log4j2.properties.
               
Running Tests:-1)Run with TestNG: Execute tests using TestNG: mvn test
               2)Run specific test cases: You can modify testng.xml to include/exclude specific test cases or groups.
               3)Run in headless mode:Update the configuration in Utilities/config.properties to enable headless mode.
               4)Test Suite is executable from command line using maven.
               
Features:-1)Page Object Model (POM) with Page Factory:Modular design for better maintainability and readability.
          2)Configurable Test Data:Test data is managed in an Excel file allowing easy modifications.
          3)Global Parameters Configuration:Managed through config files for consistent and centralized control.
          4)Browser Compatibility:Tests run on Internet Explorer, Chrome, and Firefox.
          5)Headless Mode:Option to run tests without UI for faster execution.
          6)TestNG Features:Grouping, Priority, and Enable/Disable functionalities to organize tests.
          7)Assertions:Proper usage of assertions to validate test outcomes.
          8)Error Reporting:Screenshots and extent reports for detailed analysis of failed test cases.
          9)Reusable Libraries:Modular libraries to enhance code reusability.
         10) Wait: - Implicit wait and Explicit wait is implemented.
         11) Exception Handling: - It is carried through the try and catch, finally block.
         12) Screenshot: - Error which causes it is attached in this folder. And failed test cases report
                          are in the failed testcases Extent report folder.
         13)Logging:-Logging is implemented using Log4j2. Log files are located in the logs directory.
          
Tools and Technologies:-Selenium:For automating web browser interactions.
                        Java:Programming language used for developing test scripts.
                        TestNG:Testing framework for managing and executing tests.
                        Maven:Build automation tool to manage dependencies and run tests.

Continuous Integration and Continuous Deployment (CI/CD):-
            Jenkins Integration:Integrated the test suite with Jenkins for automated builds and test execution.
            GitHub Integration:Use GitHub for version control and CI/CD pipelines.
           

Reporting:-Extent Reports are used for generating test reports. The main report is located at Reports/FlipkartExtentReport.html.  
           Screenshots of failed test cases are stored in the screenshots folder, and specific reports for failed test cases are                
           in the FailedTestCasesExtentReports folder.
           
Reusable Libraries:-Reusable libraries are created and imported as jars into the current project to enhance modularity and 
                    reusability.   
                    
Deliverables:-
           Code Archive:The complete code is shared in a zip format and uploaded to Google Drive and also contains proper   
                        documentation with the screenshots attached.
           Execution:All code files can be executed successfully.
