# Coffre - Personal finance made simple
![GitHub](https://img.shields.io/github/license/N3Shemmy3/Coffre?style=flat-square&logoColor=white&labelColor=black&color=white)
![GitHub tag (with filter)](https://img.shields.io/github/v/tag/N3Shemmy3/Coffre?style=flat-square&logoColor=white&labelColor=black&color=white)
[![Static Badge](https://img.shields.io/badge/Telegram-Content?style=flat-square&logo=telegram&logoColor=black&color=white)](https://t.me/N3Shmmy3)

Coffre is a simple and intuitive Android application designed to help you manage your personal finances effectively.  Track your income, expenses, and transfers with ease, gain insights into your spending habits, and achieve your financial goals.

## Features

*   **Transaction Recording:**  Effortlessly record your financial transactions, including:
    *   Transaction Type: Income, Expense, or Transfer
    *   Amount: Precise amount using BigDecimal
    *   Title:  A descriptive title for the transaction
    *   Date and Time:  Accurate timestamp for each transaction
    *   Notes:  Optional detailed description for context
*   **Transaction Listing:**  View a chronologically ordered list of all your transactions.
*   **Search:**  Quickly find specific transactions by searching through their descriptions.
*   **Net Balance Calculation:**  Automatically calculates and displays your current financial standing (Income - Expenses).
*   **Income and Expense Tracking:**  Monitor your total income and expenses separately to understand your cash flow.
*   **Data Persistence:** Utilizes ObjectBox for efficient and reliable local data storage.

## Architecture

Coffre follows a clean architecture pattern with a clear separation of concerns:

*   **UI Layer:**  Activities and Fragments responsible for displaying data and handling user interactions.  Uses Android Jetpack's LiveData for reactive UI updates.
*   **ViewModel Layer:**  Manages UI-related data and business logic, acting as a bridge between the UI and the data layer.
*   **Repository Layer:**  `TransactionsRepository` acts as a single source of truth for transaction data, abstracting data access from the ViewModel. Interacts directly with the ObjectBox database.
*   **Data Layer:**  ObjectBox serves as the local database, providing efficient storage and retrieval of transaction data.  Transactions are modeled as entities with properties like type, amount, title, timestamp, and notes.

## Technologies Used

*   **Java:** The BEST programming language for Android development.
*   **Android Jetpack:** A suite of libraries and tools for building robust and maintainable Android applications.  Key components include:
    *   **LiveData:** For observable data that automatically updates the UI.
    *   **ViewModel:** To manage UI-related data and survive configuration changes.
    *   **Paging 3:**  For efficient loading and display of large transaction lists in pages.
*   **ObjectBox:** A fast and lightweight NoSQL database optimized for mobile devices.
*   **Gradle Kotlin DSL:** For a more concise and expressive build configuration.
*   **Material Components:**  For a modern and consistent user interface.

## Setup and Installation

1.  **Prerequisites:**
    *   Android Studio (latest version recommended)
    *   Kotlin plugin for Android Studio

2.  **Clone the Repository:**
3. 3.  **Open in Android Studio:**

    *   Open Android Studio and select "Open an Existing Project."
    *   Navigate to the cloned repository directory and select the project.

4.  **Sync Project with Gradle Files:**

    *   Android Studio will likely prompt you to sync the project. If not, you can manually trigger it by going to "File" -> "Sync Project with Gradle Files."

5.  **Build and Run:**

    *   Once the project is synced, you can build and run the app on an emulator or a physical Android device.

## Code Structure (Key Components)

*   `dev.n3shemmy3.coffre.ui.screen.record.RecordScreen`:  Fragment for creating, editing, and deleting transactions.
*   `dev.n3shemmy3.coffre.backend.item.Transaction`:  Data class representing a financial transaction.
*   `dev.n3shemmy3.coffre.backend.item.Amount`: Data class representing the amount and type of transaction.
*   `dev.n3shemmy3.coffre.backend.viewmodel.MainViewModel`: ViewModel managing the main app logic.
*   `dev.n3shemmy3.coffre.backend.repository.TransactionsRepository`: Repository handling data access and business logic related to transactions.

## Contributing

Contributions are welcome!  Please follow these steps:

1.  Fork the repository.
2.  Create a new branch for your feature or bug fix:  `git checkout -b feature/my-new-feature` or `git checkout -b bugfix/my-bug-fix`
3.  Make your changes and commit them with clear and concise messages.
4.  Push your changes to your fork: `git push origin feature/my-new-feature`
5.  Create a pull request to the main branch of the original repository.

## Future Enhancements

*   **Account Management:**  Support for multiple accounts (e.g., checking, savings, credit cards).
*   **Categories and Tags:**  Categorize transactions for better spending analysis.
*   **Recurring Transactions:**  Automate the recording of regular income or expenses.
*   **Budgets:**  Set and track monthly or custom budgets.
*   **Reports and Visualizations:**  Generate charts and reports to visualize spending patterns.
*   **Export/Import:**  Options to export and import transaction data for backup or transfer.
*   **Cloud Sync:**  Optional cloud synchronization for data across devices.

## License

This project is licensed under the GNU General Public License v3.0.

A copy of the license can be found in the `LICENSE` file within this project or at:

https://www.gnu.org/licenses/gpl-3.0.en.html

**Summary of GPL-3.0:**

This license grants you the following freedoms:

*   **Freedom to run the program:**  You may use the software for any purpose.
*   **Freedom to study and change the program:** You have access to the source code and can modify it.
*   **Freedom to redistribute copies:** You can share the software with others.
*   **Freedom to distribute modified versions:**  You can distribute your changes or improvements, but under the same GPL-3.0 license.

**Important Considerations:**

*   **Copyleft:** Any derivative works (modifications or additions) of this software must also be licensed under GPL-3.0.  This ensures that the freedoms granted by the original license are preserved in all subsequent versions.
*   **Source Code Availability:**  If you distribute copies of the software, you must make the source code available to recipients.
*   **No Warranty:** The software is provided "as is" without any warranty.

By using, modifying, or distributing this software, you agree to the terms and conditions of the GNU General Public License v3.0.