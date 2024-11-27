# Java API Automation Project

This project is a Java-based API testing automation framework using **Cucumber** and **Rest Assured**, developed in **Eclipse IDE**. It is designed to perform automated tests for RESTful APIs and supports behavior-driven development (BDD) practices.

---

## Project Setup

### Prerequisites
- **Java JDK** (version 8 or higher)
- **Maven** (for dependency management)
- **Eclipse IDE** or any compatible Java IDE
- **Git** (optional, for version control)

### Installation Steps
1. Clone this repository:
   ```bash
   git clone https://github.com/your-username/your-repo.git
   cd your-repo
   ```
2. Open the project in Eclipse:
   - Go to 'File' > 'Open Projects from File System...'
   - Select the project folder.
3. Update dependencies:
   ```bash
   mvn clean install
   ```

## Running Tests

### Running from Eclipse
1. Right-click on the feature file in the 'src/test/resources' folder.

2. Select 'Run As' > 'Cucumber Feature'.

### Running from the Command Line
Execute the following Maven command:
```bash
mvn test
```

### Generating Reports
- Reports are generated in the 'TestResult' folder.

## Project Structure
```bash
src/
├── main/
│   └── java/│
│   └── resouces/│
└── test/
    ├── java/
    │   └── comum/
    │       ├── common_steps.java      # Common steps
    │       ├── extendReport.java      # Test result code with extend report
    │       ├── hooks.java             # Cucumber hooks
    │       ├── testRunner.java        # Execute test wirh Junit
    │       └── variables.java         # Global Variables
    │   └── pageObject/
    │       ├── carrinho_po.java      
    │       ├── login_po.java      
    │       ├── produto_po.java                 
    │       └── usuarios_po.java         
    └── resources/
        └── features/          # Cucumber feature files
            ├── carrinho.feature     
            ├── login_po.feature    
            ├── produto.feature              
            └── usuarios.feature
TestResults/
├──AutomationStatusReport
pom
```

## Dependencies
This project uses the following key dependencies:
- **Cucumber**: BDD framework for defining and executing tests.
- **Rest Assured**: Library for testing and validating RESTful services.
- **JUnit**: Test runner for executing scenarios.
- **Maven**: Build tool for managing project dependencies.

## Features
- Supports **BDD** with Gherkin syntax for clear, human-readable tests.
- Automates **RESTful API testing** with a clean, modular approach.
- Generates detailed test reports.
- Easy to scale and extend for complex projects.
