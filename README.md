# Assignment-3: Spring Boot API + Android Mobile App

This is a multi-module project containing both a Spring Boot API backend and an Android mobile app frontend.

## 📁 Project Structure

```
Assignment-3/
├── api/                          # Spring Boot API Module
│   ├── src/main/java/com/example/Assignment_3/
│   │   ├── controller/MovieController.java
│   │   ├── model/Movie.java
│   │   ├── repository/MovieRepository.java
│   │   └── config/DataInitializer.java
│   ├── src/main/resources/application.properties
│   └── build.gradle
├── mobile-app/                   # Android App Module
│   ├── app/src/main/java/com/example/movieapp/
│   │   ├── MainActivity.java
│   │   ├── Movie.java
│   │   ├── MovieAdapter.java
│   │   ├── ApiService.java
│   │   └── ApiClient.java
│   ├── app/src/main/res/
│   └── app/build.gradle
├── build.gradle                  # Root build file
├── settings.gradle              # Multi-module configuration
└── README.md
```

## 🚀 How to Run

### **Option 1: Run Both in IntelliJ (Recommended)**

1. **Open the entire project** in IntelliJ:

   - File → Open → Select the `Assignment-3` folder
   - Choose "Open as Project"

2. **Run the Spring Boot API:**

   - Navigate to: `api/src/main/java/com/example/Assignment_3/Assignment3Application.java`
   - Right-click → "Run 'Assignment3Application.main()'"
   - API will start on `http://localhost:8080`

3. **Run the Android App:**
   - Navigate to: `mobile-app/app/src/main/java/com/example/movieapp/MainActivity.java`
   - Create Android run configuration:
     - Run → Edit Configurations → + → Android App
     - Name: `MovieApp`
     - Module: `mobile-app.app`
     - Activity: `MainActivity`
   - Click the play button (▶️)

### **Option 2: Command Line**

#### **Start Spring Boot API:**

```bash
# From the root directory
./gradlew :api:bootRun
```

#### **Build and Run Android App:**

```bash
# From the root directory
./gradlew :mobile-app:app:installDebug
```

### **Option 3: Separate Projects (Alternative)**

If you prefer separate projects:

1. **For API development:**

   - Open the `api` folder in IntelliJ
   - Run `Assignment3Application.java`

2. **For Android development:**
   - Open the `mobile-app` folder in IntelliJ
   - Create Android run configuration

## 📱 Features

### **Spring Boot API:**

- RESTful API with `/api/top-20-movies` endpoint
- H2 in-memory database with 20 pre-loaded movies
- CORS enabled for mobile app access
- JPA/Hibernate for data persistence

### **Android Mobile App:**

- Native Android app written in Java
- Material Design UI with RecyclerView
- Retrofit for API communication
- Glide for image loading
- Displays movie posters, titles, ratings, and details

## 🔧 Configuration

### **API Configuration:**

- **Port**: 8080 (configurable via `SERVER_PORT`)
- **Database**: H2 in-memory (for development)
- **CORS**: Enabled for all origins

### **Android App Configuration:**

- **API URL**: `http://10.0.2.2:8080/` (for emulator)
- **Target SDK**: 34
- **Min SDK**: 24

## 🎯 API Endpoints

- `GET /api/top-20-movies` - Returns all movies

## 📋 Prerequisites

- Java 21
- Android SDK
- IntelliJ IDEA (recommended)
- Android emulator or device

## 🚨 Troubleshooting

### **If Android module not recognized:**

1. Close IntelliJ
2. Reopen the entire `Assignment-3` project
3. Wait for Gradle sync to complete
4. Check that both modules appear in Project Structure

### **If API not starting:**

1. Check if port 8080 is available
2. Verify Java 21 is installed
3. Run `./gradlew :api:bootRun` from command line

### **If Android app not connecting to API:**

1. Ensure Spring Boot API is running
2. Check API URL in `ApiClient.java`
3. Verify emulator is running

## 🎉 Success Indicators

- **API**: Spring Boot starts without errors, accessible at `http://localhost:8080/api/top-20-movies`
- **Android App**: Installs and launches on emulator, displays movie list with posters

This multi-module setup allows you to develop both the API and Android app in the same IntelliJ project while maintaining proper separation of concerns.
# Assignment4---Mobile-API-Framework
