FROM node:alpine
WORKDIR /app
COPY package.json .
RUN npm install
EXPOSE 8081

COPY . .

ENTRYPOINT [ "node", "app.js" ]