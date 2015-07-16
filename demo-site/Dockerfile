FROM google/nodejs

WORKDIR /app
ADD package.json /app/
RUN npm install
ADD . /app



EXPOSE 8888

CMD []
ENTRYPOINT ["/nodejs/bin/node", "app.js"]
