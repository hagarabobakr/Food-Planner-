# Kitchen Buddy

A feature-rich Android application designed to help users plan their weekly meals efficiently and enjoyably. With Kitchen Buddy, users can explore recipes, organize their favorite meals, and manage their weekly plans effortlessly.

---

## Table of Contents
- [Screenshots](#screenshots)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
- [API Reference](#api-reference)
- [Usage](#usage)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)

---

## Screenshots

<div>
  <img src="https://github.com/user-attachments/assets/f4911636-f63b-477a-929d-4232cd5d2d5b" width="200">
  <img src="https://github.com/user-attachments/assets/7c9817ae-915f-4d13-b7de-0feabf95a089" width="200">
  <img src="https://github.com/user-attachments/assets/c8cdb607-1489-4e9c-9cb1-9ee463974ab6" width="200">
  <img src="https://github.com/user-attachments/assets/874fe94c-d7ba-44c1-a9be-7588db704551" width="200">
</div>
<div>
  <img src="https://github.com/user-attachments/assets/9850d868-87f3-4e0c-9d68-ca615e2f36fe" width="200">
  <img src="https://github.com/user-attachments/assets/065f23fc-1806-430d-b8fb-01eb495c28d4" width="200">
  <img src="https://github.com/user-attachments/assets/f377d3ea-1b3c-4339-9aad-a453691aae0d" width="200">
  <img src="https://github.com/user-attachments/assets/fecea3bc-aa86-485a-beaa-97c47722dc14" width="200">
</div>
<div>
  <img src="https://github.com/user-attachments/assets/eba6791f-3ea2-4da9-a733-b0973f84e1f3" width="200">
  <img src="https://github.com/user-attachments/assets/8c6df149-71b3-4eea-b95c-e004d91755ed" width="200">
  <img src="https://github.com/user-attachments/assets/79157284-7978-4b36-8f3b-934d01e1f852" width="200">
  <img src="https://github.com/user-attachments/assets/3066015d-cd0a-4bc8-a05f-8a37b0b84dd8" width="200">
</div>
<div>
  <img src="https://github.com/user-attachments/assets/54ab4d10-2b3b-4402-8bfc-891eab4c0ac5" width="200">
  <img src="https://github.com/user-attachments/assets/37a31ab1-a041-44fc-b582-55b34c2ce9ab" width="200">
</div>


---

## Features

### Core Functionalities
- **Meal of the Day:** Get a daily meal suggestion for inspiration.
- **Search Options:** Search meals by country, ingredient, or category.
- **Category and Country Exploration:**
  - Browse categories to discover new recipes.
  - Explore popular meals from different countries.
- **Favorites:**
  - Add meals to your favorites for offline access.
  - Remove meals from your favorites with ease.

### Planning and Organization
- **Weekly Planner:** Add meals to your weekly plan and view them offline.
- **Synchronization:** Backup and restore user data via Firebase for seamless access across devices.

### Authentication Options
- **Social logins**: Google.
- **Guest mode** to explore the app without signing up.

### Interactive Meal Details
- Meal name, image, origin, ingredients (with images if possible), preparation steps, and embedded video tutorials.

### UI Enhancements
- **Splash screen animation** using Lottie.
- **Material Design** for a modern and intuitive user interface.
- **Calendar integration** for meal planning.

---

## Technologies Used
- **Programming Language:** Java
- **Architecture Pattern:** MVP (Model-View-Presenter)
- **Local Storage:** Room Database
- **Cloud Storage and Authentication:** Firebase
- **API Integration:** TheMealDB API
- **UI/UX:** Material Design and Lottie Animations

---

## Setup and Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/hagarabobakr/Food-Planner-.git
    ```

2. Open the project in Android Studio.

3. Sync the project with Gradle files.

4. Obtain an API key from [TheMealDB](https://www.themealdb.com/) and add it to the `gradle.properties` file:
    ```properties
    THEMEALDB_API_KEY=your_api_key
    ```

5. Run the project on an emulator or physical device.

---

## API Reference

Kitchen Buddy integrates with TheMealDB API to fetch meal data. Key endpoints used include:

- **Random Meal:** `/random.php`
- **Search Meal:** `/search.php?s={meal_name}`
- **Categories:** `/categories.php`
- **Meals by Country:** `/filter.php?a={country_name}`

---

## Usage

1. Launch the application.
2. Explore daily meals, categories, or search for meals.
3. Add meals to your favorites or weekly planner.
4. Synchronize your data with Firebase for cross-device access.

---

## Future Enhancements
- Add notifications to remind users of planned meals.
- Introduce AI-based meal suggestions based on user preferences.
- Support for multiple languages.

