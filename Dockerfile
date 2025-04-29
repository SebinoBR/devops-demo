FROM node:18-alpine
WORKDIR /app
# Since we're using context: ./frontend in the workflow,
# we don't need to specify "frontend/" in the COPY commands
COPY package.json ./
COPY package-lock.json ./
RUN npm ci
COPY . .
RUN npm run build
EXPOSE 3000
CMD ["npm", "start"]
