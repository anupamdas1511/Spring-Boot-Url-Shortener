# Spring Boot URL Shortener

A simple and efficient **URL Shortener** built with **Spring Boot** and **MongoDB**.  
This project allows users to shorten long URLs into short, shareable links and redirect them seamlessly.

---

## 🚀 Features
- ✂️ Shorten long URLs into unique short links
- 🔁 Redirect short links to original URLs
- 📅 Store and manage links in **MongoDB**
- ⚡ RESTful API built with **Spring Boot**
- 🔐 Input validation for secure URLs

---

## ⚙️ Setup & Installation

1. **Clone the repository**
    ```bash
   git clone https://github.com/anupamdas1511/Spring-Boot-Url-Shortener.git
   ```
   
2. **Configure MongoDB**
    In the Environment Variable set:
    ```bash
   MONGO_URL={your_mongo_url} # local or atlas
   ```
   
3. Build and Run
    ```bash
   mvn spring-boot:run
   ```
   
---

## 📌 API Endpoints

### 1️⃣ Shorten a URL

```http request
POST /api
```

*Request JSON body*

```json
{
  "url": "{your_long_url}",
  "key": "{YOUR_SHORT_KEY}"
}
```

*Response*
```json
{
  "shortenedUrl": "http://localhost:8081/{YOUR_SHORT_KEY}"
}
```

### 2️⃣ Redirect to Original URL
```http request
GET http://localhost:8081/{YOUR_SHORT_KEY}
```

Example:
`http://localhost:8080/abc123` → Redirects to `https://www.example.com/very/long/url`

## 📊 Future Enhancements

- 📈 Analytics (track clicks, timestamps, IPs)
- 🧑‍💻 User authentication (login, manage personal links)
- ⏳ Expiry date for short URLs
- 🌍 Custom short codes