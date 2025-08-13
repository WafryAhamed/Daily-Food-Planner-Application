# 🍽️ ChefBuddy - Android Apppp

Chef Buddy is your personal meal planning assistant – built with Java, MVP architecture, and TheMealDB API.

Whether you're a busy student, a working parent, or simply someone who loves good food, **Chef Buddy helps you stay organized, save time, reduce food waste**, and make every meal delicious.



## 🎯 Purpose

Chef Buddy is designed to help you take control of your kitchen and plan your meals effortlessly.

### What You Can Do with Chef Buddy:
- 🔍 **Search Recipes by Ingredients**  
  Don’t know what to cook? Just enter the ingredients you have, and Chef Buddy will find the best recipe ideas — no more food going to waste!

- ❤️ **Save Your Favourites**  
  Loved that dish? Tap to save it to your favourites list so you can find it instantly next time you’re hungry for it.

- 📅 **Plan Your Meals for the Week**  
  Use the built-in calendar to assign meals to each day. Eat balanced, avoid food waste, and eliminate the “What’s for dinner?” stress.

- 📖 **Step-by-Step Recipe Instructions**  
  Each recipe includes full ingredient lists, instructions, and helpful cooking tips — perfect for beginners.



## 🚀 Features

- 🔎 Discover meals by **ingredient**, **category**, or **area**
- ❤️ Save favorite meals
- 📅 Plan meals using a **calendar interface**
- 📖 View detailed recipe instructions with ingredients



## 🛠️ Tech Stack

| Layer        | Technology                          |
|--------------|-------------------------------------|
| Language     | Java                                |
| Architecture | MVP (Model-View-Presenter)          |
| API          | [TheMealDB](https://www.themealdb.com/api.php) |
| Networking   | Retrofit + Gson                     |
| Database     | Room (SQLite)                       |
| UI           | Material Design + Navigation Component |
| Images       | Glide                               |
| Animation    | Lottie                              |



## Project Development Tools & Version Details

- **Development Tool:** Android Studio (official IDE for Android development)
- **Build System:** Gradle with Kotlin DSL
- **Android Gradle Plugin (AGP):** Version 8.9.2
- **Gradle Version:** 8.9.2 (via AGP)
- **Android SDK:** API 34 (compileSdk = 34)
- **Minimum SDK:** API 24 (minSdk = 24)
- **Target SDK:** API 34 (targetSdk = 34)
- **Language:** Java (sourceCompatibility = JavaVersion.VERSION_1_8)

### Key Dependencies
- **AndroidX Libraries:** AppCompat, Material, ConstraintLayout, Lifecycle, Navigation
- **Retrofit 2.11.0** (networking)
- **Gson 2.11.0** (JSON parsing)
- **Glide 4.16.0** (image loading)
- **Room 2.6.1** (database)
- **Lottie 5.2.0** (animations)
- **JUnit 4.13.2** (testing)


## 🔧 Getting Started

### Clone the Repository:
To clone the repository to your local machine, run the following command:

```bash
git clone https://github.com/WafryAhamed/Daily-Food-Planner-Application.git
```

### Download the Repository:
If you prefer not to use Git, you can download the repository as a ZIP file by clicking the **Code** button on the GitHub repository page and selecting **Download ZIP**. After downloading, extract the ZIP file to access the project files.

### Fork the Repository:
If you want to contribute to this project, you can **fork** the repository to your own GitHub account. To fork the repository:

1. Go to the GitHub repository page: [ChefBuddy](https://github.com/WafryAhamed/Daily-Food-Planner-Application.git)
2. Click the **Fork** button at the top-right corner of the page.
3. Once forked, you can clone your own version of the repository:

   ```bash
   git clone https://github.com/WafryAhamed/Daily-Food-Planner-Application.git
   ```

Now you can work on your own copy and push changes to your forked repository, then create a pull request to contribute your changes to the original repository.




⚠️ **Error Fixes**  
In case you face any issues or errors, here are a few common fixes:

### 1. Gradle Build Errors:
If you face an issue related to Gradle builds, try the following:
   ```bash
   ./gradlew clean
   ./gradlew build
   ```
   This will clean the Gradle cache and rebuild the project. If the error persists, try invalidating caches in Android Studio:
   - **File > Invalidate Caches / Restart > Invalidate and Restart.**


### 2. Force Fetch Latest Changes:
If you're not seeing the latest changes or you're facing an issue where the repository isn’t up to date, run the following command to force fetch the latest changes:
   ```bash
   git fetch --all
   git reset --hard origin/<your-branch-name>
   ```
   This will fetch the latest updates from the remote repository and reset your local branch to match the remote.

### 3. Removing a Remote Repository:
If you need to remove a remote repository from your project (for example, if the URL has changed or you no longer need it), use the following command:
   ```bash
   git remote remove origin
   ```
   This will remove the current remote configuration. You can add a new remote using:
   ```bash
   git remote add origin <new-repository-url>
   ```

### 4. Resolve Merge Conflicts:
If you encounter a merge conflict when pulling the latest changes, Git will indicate where the conflicts occur. To resolve conflicts:
   ```bash
   git status
   ```
   This will show files with conflicts. Open the conflicting files and resolve them manually. Once resolved, add the files back to the staging area:
   ```bash
   git add <resolved-file>
   ```
   After resolving all conflicts, complete the merge:
   ```bash
   git commit
   ```
   If necessary, you can abort a merge:
   ```bash
   git merge --abort
   ```

### .5 Force Push to Overwrite Remote Changes:
If you need to overwrite changes on the remote (e.g., in case of force-pushing), use the following command:
   ```bash
   git push --force origin <your-branch-name>
   ```
   **Warning:** Force pushing will overwrite the remote branch and should be used cautiously.

### 6. Viewing and Switching Branches:
To list all available branches and see which one you're currently on:
   ```bash
   git branch
   ```
   To switch to a different branch:
   ```bash
   git checkout <branch-name>
   ```
   Or, with the latest versions of Git, you can use:
   ```bash
   git switch <branch-name>
   ```

---

## 🔧 Git Commit & Push Instructions

### 1. **Check the status of your files** to see what has changed:

```bash
git status
```

### 2. **Add your changes** to the staging area:

```bash
git add .
```

### 3. **Commit your changes** with a meaningful message:

```bash
git commit -m "Your commit message"
```

### 4. **Push your changes** to the `main` branch using the `-u` flag:

```bash
git push -u origin main
```

What this command does:
- Pushes the `main` branch to the remote repository (`origin`).
- Sets the upstream reference, so you can use `git push` in the future without specifying the remote and branch.

After this, you can simply push future changes with:

```bash
git push
```


4. **Push changes to your branch:**
   ```bash
   git push origin <your-branch-name>
   ```
   If you're working on the main branch, replace `<your-branch-name>` with `main`. Make sure to pull any recent changes before pushing:
   ```bash
   git pull origin <your-branch-name>
   ```





