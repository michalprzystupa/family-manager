FROM node:8.11.3-alpine

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app

COPY . .

RUN npm install

EXPOSE 4200

CMD ["npm", "start"]