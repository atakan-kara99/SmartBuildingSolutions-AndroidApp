# Smart Building Solutions – Android App

The **Smart Building Solutions Mobile App** enables construction site workers and on-site personnel to track and update project progress in real time. It allows users to view project and contract data, update performance item statuses, and upload photo documentation directly from the construction site — even when offline.

> **Note:** This repository contains only the **Android client application**.
> Backend and web components are maintained in separate repositories.

---

## Features

### ✅ Key Capabilities

* Login & authentication against central system
* View assigned projects, contracts & performance items
* Update status of performance items (e.g. completed, denied)
* Upload photos with comments to document site progress
* Offline mode — store updates locally and sync when online
* Local caching to reduce network usage

### 👥 User Role

* **AppUser**: On-site construction worker or supervisor with restricted permissions

  * Can only view & update items they are authorized to see

---

## Technology Stack

| Category        | Technology           |
| --------------- | -------------------- |
| Language        | Java (JDK 11)        |
| IDE             | Android Studio       |
| Build System    | Gradle               |
| Networking      | OkHttp               |
| Local Database  | Room (SQLite)        |
| Testing         | JUnit, Espresso      |
| Camera API      | CameraX              |
| Minimum Android | Android 6.0 (API 23) |

---

## Architecture

The app follows a layered architecture:

| Layer         | Responsibility                      |
| ------------- | ----------------------------------- |
| Activities    | UI screens and user interactions    |
| Data Layer    | Room database for offline storage   |
| Network Layer | REST communication via OkHttp       |
| Sync Module   | Uploads offline changes when online |
| Camera Module | Capture and attach photos           |

---

## Core Functionality Flow

1. User logs in
2. App loads project & contract data
3. User selects a performance item
4. User updates status **OR** attaches a photo + comment
5. If offline → changes saved to cache
6. When online → app syncs changes to server

---

## Screens (UI Flow)

* Login Screen
* Project List
* Contract / Billing Unit list
* Performance Item detail
* Photo capture + comment submission
* Offline sync indicator

---

## Offline Behavior

When offline:

* Status changes and photos are stored locally
* App notifies user that data is pending sync
* Once connection is restored → automatic upload

---

## Testing

* App logic tested with **JUnit**
* UI tested with **Espresso**
* Device camera tested using CameraX test utilities
