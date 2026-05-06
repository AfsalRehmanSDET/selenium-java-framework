# [...](asc_slot://start-slot-7)Selenium Java Test Automation Framework
A robust, scalable test automation framework built using Java, Selenium WebDriver, Maven, and TestNG. This framework is designed for efficient web UI automation with a focus on maintainability and data-driven execution.

## 🚀 Tech Stack
- [...](asc_slot://start-slot-11)**Programming Language**: Java
- [...](asc_slot://start-slot-13)**Automation Tool**: Selenium WebDriver
- [...](asc_slot://start-slot-15)**Test Framework**: TestNG
- **Data Management**: Apache POI (Excel Read/Write)
- [...](asc_slot://start-slot-17)**Build Tool**: Maven

## [...](asc_slot://start-slot-19)✨ Key Features
- [...](asc_slot://start-slot-21)**Data-Driven Testing**: Externalize test data into Excel sheets to run the same test with multiple data sets.
- **Excel Utilities**: Custom logic to read from and write results back to `.xlsx` files using Apache POI.
- [...](asc_slot://start-slot-23)**Cross-Browser Support**: Configurable execution across Chrome, Firefox, and Edge.
- **Structured Reporting**: Easy-to-read execution logs and test results.

## 📁 Repository Structure
- `src/main/java` - Contains Page Objects, Base Class, and **Excel Utilities**.
- `src/test/java` - Contains TestNG test scripts and test suites.
- `src/test/resources` - Stores **Excel test data files** and configuration properties.
- `pom.xml` - Project dependencies (now includes Apache POI).
- `testng.xml` - Test suite configuration for execution.

## 🛠️ Prerequisites
- [Java JDK 11+](https://www.oracle.com/java/technologies/downloads/)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [...](asc_slot://start-slot-25)A Java IDE (IntelliJ IDEA, Eclipse, etc.)
- [...](asc_slot://start-slot-27)Web Browsers (Chrome, Firefox, Edge)

## ⚙️ Getting Started

### 1. [...](asc_slot://start-slot-29)Clone the repository
```bash
git clone https://github.com/AfsalRehmanSDET/selenium-java-framework.git
cd selenium-java-framework
