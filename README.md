# GitHub Trending CLI

A command-line application that fetches and displays the most popular GitHub repositories created within a specified time period.

This project was built to practice working with REST APIs, HTTP requests, JSON parsing, command-line applications, and Maven using modern Java.

---

## Features

- View trending GitHub repositories
- Filter by duration:
    - Day
    - Week
    - Month
    - Year
- Limit the number of displayed repositories
- Clean command-line output
- Parses JSON using Jackson
- Command-line argument parsing with Picocli

---

## Tech Stack

- Java 21
- Maven
- Picocli
- Jackson
- Java HttpClient
- GitHub REST API

---

## Project Structure

```
src
└── main
    └── java
        └── org.example
            ├── Main.java
            ├── APICall.java
            ├── Parser.java
            ├── SearchResponse.java
            └── Repository.java
```

---

## Usage

Clone the repository:

```bash
git clone https://github.com/<your-username>/github-trending-cli.git
```

Move into the project:

```bash
cd github-trending-cli
```

Run the application:

```bash
mvn exec:java -Dexec.args="--duration month --limit 10"
```

---

## Command-Line Options

| Option | Description | Default |
|---------|-------------|---------|
| `--duration` | day, week, month, year | week |
| `--limit` | Number of repositories | 10 |

Example:

```bash
mvn exec:java -Dexec.args="--duration year --limit 20"
```

---

## Example Output

```
----------------------------------------------
#1 grok-build

Description : SpaceXAI's coding agent harness and TUI.
Language    : Rust
Stars       : 22,491
----------------------------------------------

#2 ...
```

---

## What I Learned

This project helped me practice:

- Building command-line applications
- Using Java HttpClient
- Working with REST APIs
- Constructing URLs and query parameters
- URL encoding
- Parsing JSON with Jackson
- Java Records
- Enums
- Maven project structure
- Separation of concerns

---

## Future Improvements

- Better exception handling
- Colored terminal output
- Pagination support
- Export results to JSON or CSV
- Unit tests
- Logging
- GitHub Personal Access Token support for higher API rate limits

---

## License

This project is licensed under the MIT License.