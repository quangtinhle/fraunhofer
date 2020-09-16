FROM node:alpine

WORKDIR /app

ADD package.json /app
RUN npm install

ADD app.js /app

EXPOSE 8181

ENTRYPOINT [ "node", "app.js" ]