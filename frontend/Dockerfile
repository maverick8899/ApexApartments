FROM node:18.18-alpine AS builder
# Add a work directory
WORKDIR /app
COPY package.json .
COPY . .
RUN npm install 
RUN npm run build

# Bundle static assets with nginx
FROM nginx:1.21.0-alpine  
# Copy built assets from builder
COPY --from=builder /app/build /usr/share/nginx/html
# Add your nginx.conf
COPY nginx.conf /etc/nginx/nginx.conf
# Expose port
EXPOSE 80
# Start nginx
CMD ["nginx", "-g", "daemon off;"]
