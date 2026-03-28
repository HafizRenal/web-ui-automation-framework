# Web UI Automation Framework

A web UI automation testing framework built with **Java**, **Selenium WebDriver**,
**Cucumber (BDD)**, and **Gradle**.

---

## Tech Stack

| Tool | Version | Purpose |
|------|---------|---------|
| Java | 17 (LTS) | Programming language |
| Selenium WebDriver | 4.21.0 | Browser automation |
| Cucumber JVM | 7.18.0 | BDD test framework |
| JUnit | 4.13.2 | Test runner |
| WebDriverManager | 5.9.2 | Auto browser driver setup |
| Gradle | 8.x | Build management |

---

## Project Structure
```
web-ui-automation-framework/
└── app/
    ├── build.gradle
    └── src/
        └── test/
            ├── java/org/ui/
            │   ├── pages/
            │   │   └── LoginPage.java       # Page Object Model
            │   ├── runner/
            │   │   └── TestRunner.java      # Cucumber runner
            │   └── steps/
            │       └── LoginSteps.java      # Step definitions
            └── resources/features/
                └── login.feature           # Gherkin test scenarios
```

---

## Design Pattern: Page Object Model (POM)

Each web page is modeled as a Java class. Web elements and actions
are encapsulated inside the class, keeping test logic clean and maintainable.

**LoginPage.java** represents the login page of the target website and provides:
- `open()` — navigate to the login page
- `enterUsername(String)` — fill username field
- `enterPassword(String)` — fill password field
- `clickLogin()` — click the login button
- `getFlashMessage()` — read success/error message
- `isOnSecurePage()` — verify successful login by URL

---

## Test Scenarios

Target website: [https://the-internet.herokuapp.com/login](https://the-internet.herokuapp.com/login)

Valid credentials: `tomsmith` / `SuperSecretPassword!`

### Positive Test
| Scenario | Expected Result |
|----------|----------------|
| Login with valid credentials | Redirected to `/secure`, success message shown |

### Negative Tests
| Scenario | Expected Result |
|----------|----------------|
| Login with invalid username | Error message shown |
| Login with invalid password | Error message shown |

### Boundary Tests
| Scenario | Expected Result |
|----------|----------------|
| Login with empty username | Error message shown |
| Login with empty password | Error message shown |
| Login with very long username (300 chars) | Error message shown |

---

## How to Run

### Prerequisites
- Java 17 installed
- Google Chrome installed
- Internet connection (WebDriverManager downloads ChromeDriver automatically)

### Run all tests
```bash
./gradlew :app:test
```

### Run specific tag
```bash
./gradlew :app:test --tests "org.ui.runner.TestRunner"
```

---

## Test Report

After running, the HTML report is generated at:
```
app/target/cucumber-report/report.html
```

Open it in any browser to view detailed results.

---

## Sample Test Output
```
6 Scenarios (6 passed)
18 Steps (18 passed)
BUILD SUCCESSFUL
```