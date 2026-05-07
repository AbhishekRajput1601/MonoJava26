# Quick Start Guide

## Step 1: Database Setup (5 minutes)

1. Open MySQL client or terminal
2. Run the commands in `database_setup.sql`:
   ```sql
   CREATE DATABASE IF NOT EXISTS student_course_db;
   USE student_course_db;
   
   -- [Copy all commands from database_setup.sql and execute]
   ```

## Step 2: Configure Database Connection (2 minutes)

Edit: `src/main/java/com/studentcourse/util/DBConnection.java`

Update these lines if your MySQL credentials are different:
```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/student_course_db";
private static final String DB_USER = "root";        // Change if needed
private static final String DB_PASSWORD = "";        // Change if needed
```

## Step 3: Build the Project (5 minutes)

Open command prompt and run:
```bash
cd E:\MonoJava\servlet mini project
mvn clean install
```

Or if Maven is not installed, use your IDE to build the project.

## Step 4: Deploy to Tomcat (5 minutes)

### Option A: Using IDE
1. Configure Tomcat server in your IDE
2. Add the project as a Dynamic Web Project
3. Run on Tomcat Server

### Option B: Manual Deployment
1. Copy `target/servlet_mini_project.war` to `TOMCAT_HOME/webapps/`
2. Start Tomcat
3. Access: `http://localhost:8080/servlet_mini_project`

## Step 5: Login and Test (2 minutes)

1. Navigate to: `http://localhost:8080/servlet_mini_project`
2. Click "Admin Login"
3. Enter credentials:
   - **Username:** admin
   - **Password:** admin123
4. Click "Login"

## Step 6: Test Features

### Add Student:
1. Click "Add Student" from dashboard
2. Fill the form with sample data
3. Click "Add Student" button
4. Verify student appears in "Student List"

### Add Course:
1. Click "Add Course" from dashboard
2. Fill the form with sample data
3. Click "Add Course" button
4. Verify course appears in "Course List"

### Register Student:
1. Click "Register Student to Course" from dashboard
2. Select student and course from dropdowns
3. Click "Register Student" button
4. Verify registration in "Registration List"

## Troubleshooting

### Issue: MySQL Connection Error
**Solution:**
- Check MySQL is running: `mysql -u root -p`
- Verify database exists: `SHOW DATABASES;`
- Update DB_PASSWORD in DBConnection.java if you set MySQL password

### Issue: Port 8080 Already in Use
**Solution:**
- Change Tomcat port in `TOMCAT_HOME/conf/server.xml`
- Find line with `<Connector port="8080"` and change port number
- Restart Tomcat

### Issue: "resource not found" error
**Solution:**
- Verify WAR file is in `TOMCAT_HOME/webapps/`
- Check application name matches URL
- Wait 10-15 seconds after deploying (Tomcat needs time to extract WAR)

### Issue: JSP pages showing as downloads
**Solution:**
- Ensure Tomcat has JSP support (should be default)
- Check servlet container is correctly configured
- Restart Tomcat

## Project Structure Created

```
E:\MonoJava\servlet mini project/
├── pom.xml                           # Maven configuration
├── README.md                         # Full documentation
├── QUICKSTART.md                     # This file
├── database_setup.sql                # SQL setup script
│
├── src/
│   ├── main/
│   │   ├── java/com/studentcourse/
│   │   │   ├── controller/           # 16 Servlets
│   │   │   ├── dao/                  # 4 DAO classes
│   │   │   ├── model/                # 4 Model classes
│   │   │   └── util/                 # Database connection utility
│   │   │
│   │   └── webapp/
│   │       ├── index.html            # Landing page
│   │       ├── login.jsp             # Login page
│   │       ├── css/style.css         # Stylesheet
│   │       └── WEB-INF/
│   │           ├── views/            # 10 JSP pages
│   │           └── web.xml           # Web configuration
│   │
│   └── test/                         # Test folder (optional)
│
└── target/                           # Generated build files
    └── servlet_mini_project.war      # Deployable WAR file
```

## Project Statistics

- **16 Servlet Classes** for request handling
- **4 DAO Classes** for database operations
- **4 Model Classes** representing entities
- **11 JSP Pages** for UI
- **1 HTML Landing Page**
- **1 CSS Stylesheet** with responsive design
- **4 Database Tables** with proper relationships

## Key Features Implemented

✅ Admin login with session management  
✅ Remember username with cookies  
✅ Dashboard with statistics  
✅ Student CRUD operations  
✅ Course CRUD operations  
✅ Student-Course registration  
✅ Request forwarding (RequestDispatcher)  
✅ Redirect after successful operations  
✅ Form validation  
✅ Error handling  
✅ Protected pages (session required)  
✅ Responsive CSS design  

## Default Admin Credentials

- **Username:** admin
- **Password:** admin123

## System Requirements

- **JDK:** 11 or higher
- **Apache Tomcat:** 9.0 or higher
- **MySQL:** 5.7 or higher
- **Maven:** 3.6 or higher (optional, can build with IDE)
- **Browser:** Any modern browser (Chrome, Firefox, Edge, Safari)

## File Sizes

- **pom.xml:** Configured for Jakarta Servlet 6.0
- **All Java files:** ~3500 lines of code
- **All JSP files:** ~1200 lines of JSP/HTML
- **CSS:** ~400 lines
- **Web configuration:** Standard servlet container format

## Next Steps

1. Run the application
2. Test all features
3. Check console logs for servlet lifecycle messages
4. Review code to understand the architecture
5. Modify and extend as needed

## Common Customizations

### Change Admin Username
Edit `database_setup.sql`:
```sql
INSERT INTO admin (username, password) VALUES ('your_username', 'your_password');
```

### Change Database Name
Edit `DBConnection.java`:
```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/your_db_name";
```

### Change Tomcat Port
Edit `TOMCAT_HOME/conf/server.xml`:
```xml
<Connector port="9090" protocol="HTTP/1.1" .../>
```

## Support

Refer to README.md for complete documentation.

---

**Ready to start?** Follow Step 1 above!

