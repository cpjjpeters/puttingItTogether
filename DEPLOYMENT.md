# Deployment Guide

This guide covers various deployment options for the Putting It Together application.

## Prerequisites

- **Java 23** (Zulu OpenJDK recommended)
- **Maven 3.6+**
- **Docker** (for containerized deployment)

## Local Development Deployment

### Method 1: Using the Helper Script (Recommended)

```bash
# Make the script executable
chmod +x run-with-java23.sh

# Run the application
./run-with-java23.sh
```

### Method 2: Manual Setup

```bash
# Set Java 23 environment (macOS)
export JAVA_HOME=/Library/Java/JavaVirtualMachines/zulu-23.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH

# Verify Java version
java -version

# Build the application
mvn clean package -DskipTests

# Run the application
java -jar target/puttingItTogether-0.0.1-SNAPSHOT.jar
```

### Method 3: Maven Spring Boot Plugin

```bash
# Set Java environment first
export JAVA_HOME=/Library/Java/JavaVirtualMachines/zulu-23.jdk/Contents/Home

# Run directly with Maven
mvn spring-boot:run
```

## Docker Deployment

### Build Docker Image

```bash
# Build the image
docker build -t putting-it-together .

# Verify the image was created
docker images | grep putting-it-together
```

### Run with Docker

```bash
# Run the container
docker run -d \
  --name putting-it-together-app \
  -p 8686:8686 \
  putting-it-together

# Check container status
docker ps

# View application logs
docker logs putting-it-together-app

# Stop the container
docker stop putting-it-together-app

# Remove the container
docker rm putting-it-together-app
```

### Docker Compose (Optional)

Create a `docker-compose.yml` file:

```yaml
version: '3.8'

services:
  app:
    build: .
    ports:
      - "8686:8686"
    environment:
      - SPRING_PROFILES_ACTIVE=docker
    volumes:
      - ./data:/app/data
    restart: unless-stopped
```

Run with Docker Compose:
```bash
docker-compose up -d
```

## Production Deployment

### Environment Variables

For production deployment, consider setting these environment variables:

```bash
# Server configuration
export SERVER_PORT=8686
export SPRING_PROFILES_ACTIVE=production

# Database configuration (if using external database)
export SPRING_DATASOURCE_URL=jdbc:h2:file:/opt/app/data/togetherdb
export SPRING_DATASOURCE_USERNAME=sa
export SPRING_DATASOURCE_PASSWORD=your_secure_password

# JPA configuration
export SPRING_JPA_HIBERNATE_DDL_AUTO=validate
export SPRING_JPA_SHOW_SQL=false

# Security (if implemented)
export SPRING_SECURITY_USER_NAME=admin
export SPRING_SECURITY_USER_PASSWORD=your_admin_password

# Logging
export LOGGING_LEVEL_ROOT=INFO
export LOGGING_LEVEL_BE_IPETERS_PUTTINGITTOGETHER=DEBUG
```

### Systemd Service (Linux)

Create `/etc/systemd/system/putting-it-together.service`:

```ini
[Unit]
Description=Putting It Together Application
After=network.target

[Service]
Type=simple
User=appuser
Group=appuser
WorkingDirectory=/opt/putting-it-together
ExecStart=/usr/bin/java -jar /opt/putting-it-together/puttingItTogether-0.0.1-SNAPSHOT.jar
Restart=always
RestartSec=10

Environment=JAVA_HOME=/usr/lib/jvm/java-23-openjdk
Environment=SPRING_PROFILES_ACTIVE=production
Environment=SERVER_PORT=8686

[Install]
WantedBy=multi-user.target
```

Enable and start the service:
```bash
sudo systemctl daemon-reload
sudo systemctl enable putting-it-together
sudo systemctl start putting-it-together
sudo systemctl status putting-it-together
```

## Cloud Deployment

### AWS EC2

1. **Launch an EC2 instance** with Amazon Linux 2
2. **Install Java 23**:
   ```bash
   sudo yum update -y
   sudo yum install -y java-23-amazon-corretto
   ```
3. **Copy application files**:
   ```bash
   scp -i your-key.pem target/puttingItTogether-0.0.1-SNAPSHOT.jar ec2-user@your-instance:/home/ec2-user/
   ```
4. **Run the application**:
   ```bash
   java -jar puttingItTogether-0.0.1-SNAPSHOT.jar
   ```

### AWS Elastic Beanstalk

1. **Create application archive**:
   ```bash
   cp target/puttingItTogether-0.0.1-SNAPSHOT.jar application.jar
   zip application.zip application.jar
   ```

2. **Deploy via EB CLI**:
   ```bash
   eb init
   eb create production
   eb deploy
   ```

### Google Cloud Platform (Cloud Run)

1. **Build and push Docker image**:
   ```bash
   # Tag image for GCR
   docker tag putting-it-together gcr.io/your-project/putting-it-together

   # Push to registry
   docker push gcr.io/your-project/putting-it-together
   ```

2. **Deploy to Cloud Run**:
   ```bash
   gcloud run deploy putting-it-together \
     --image gcr.io/your-project/putting-it-together \
     --port 8686 \
     --allow-unauthenticated \
     --region us-central1
   ```

### Heroku

1. **Create Procfile**:
   ```
   web: java -jar target/puttingItTogether-0.0.1-SNAPSHOT.jar --server.port=$PORT
   ```

2. **Deploy**:
   ```bash
   heroku create your-app-name
   git push heroku main
   ```

## Database Configuration

### Development (Default)
```properties
# H2 file-based database
spring.datasource.url=jdbc:h2:file:~/databaseH2/togetherdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=create-drop
```

### Production Recommendations

For production, consider using a more robust database:

#### PostgreSQL
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/puttingittogether
spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
```

#### MySQL
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/puttingittogether
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
```

## Monitoring and Health Checks

### Health Check Endpoint

The application provides a basic health check:
- **URL**: `http://localhost:8686/actuator/health` (if actuator is enabled)
- **Alternative**: `http://localhost:8686/home` (returns 200 if app is running)

### Application Metrics

Consider adding Spring Boot Actuator for monitoring:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Configuration:
```properties
management.endpoints.web.exposure.include=health,info,metrics
management.endpoint.health.show-details=always
```

## Load Balancer Configuration

### Nginx Configuration

```nginx
upstream putting-it-together {
    server localhost:8686;
    # Add more servers for load balancing
    # server localhost:8687;
}

server {
    listen 80;
    server_name your-domain.com;

    location / {
        proxy_pass http://putting-it-together;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }

    # Static resources (if served separately)
    location /css/ {
        alias /path/to/static/css/;
        expires 1y;
        add_header Cache-Control "public, immutable";
    }

    location /js/ {
        alias /path/to/static/js/;
        expires 1y;
        add_header Cache-Control "public, immutable";
    }
}
```

## Security Considerations

### HTTPS Configuration

For production, always use HTTPS:

```properties
# Enable HTTPS
server.ssl.enabled=true
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=your_keystore_password
server.ssl.key-store-type=PKCS12
server.ssl.key-alias=your_key_alias

# Redirect HTTP to HTTPS
server.ssl.require-ssl=true
```

### Firewall Rules

Ensure only necessary ports are open:
- **Port 8686**: Application access
- **Port 22**: SSH access (for management)
- **Port 80/443**: HTTP/HTTPS (if using reverse proxy)

## Backup and Recovery

### Database Backup

For H2 file-based database:
```bash
# Backup
cp ~/databaseH2/togetherdb.mv.db ~/backups/togetherdb-$(date +%Y%m%d).mv.db

# Restore
cp ~/backups/togetherdb-20241009.mv.db ~/databaseH2/togetherdb.mv.db
```

### Application Backup

```bash
# Backup application and config
tar -czf putting-it-together-backup-$(date +%Y%m%d).tar.gz \
    puttingItTogether-0.0.1-SNAPSHOT.jar \
    application.properties \
    ~/databaseH2/
```

## Troubleshooting

### Common Issues

1. **Java version mismatch**:
   ```bash
   # Verify Java version
   java -version
   # Should show Java 23
   ```

2. **Port already in use**:
   ```bash
   # Find process using port 8686
   lsof -i :8686
   # Kill the process if needed
   kill -9 <PID>
   ```

3. **Database connection issues**:
   ```bash
   # Check database file permissions
   ls -la ~/databaseH2/
   # Ensure write permissions
   chmod 664 ~/databaseH2/togetherdb.mv.db
   ```

4. **Memory issues**:
   ```bash
   # Run with more memory
   java -Xmx1G -Xms512m -jar puttingItTogether-0.0.1-SNAPSHOT.jar
   ```

### Logs

Check application logs for debugging:
```bash
# View logs (if using systemd)
sudo journalctl -u putting-it-together -f

# View Docker logs
docker logs putting-it-together-app -f
```

## Performance Tuning

### JVM Options

```bash
java -jar \
  -Xms512m \
  -Xmx2G \
  -XX:+UseG1GC \
  -XX:MaxGCPauseMillis=200 \
  -XX:+UseStringDeduplication \
  puttingItTogether-0.0.1-SNAPSHOT.jar
```

### Application Properties

```properties
# Connection pool settings
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=20000

# JPA performance
spring.jpa.hibernate.jdbc.batch_size=20
spring.jpa.hibernate.order_inserts=true
spring.jpa.hibernate.order_updates=true
```