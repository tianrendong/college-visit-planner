FROM timbru31/java-node:11-jdk-erbium

RUN apt update
# install maven
RUN apt install -y maven
# update to npm
RUN npm install -g npm

# "COPY" all files from the current directory over to Docker.
COPY . /app
WORKDIR /app

# build backend
RUN cd backend && mvn clean package
# build frontend (static)
RUN cd frontend && npm install && npm run build
# run server
WORKDIR "/app/backend"
CMD [ "./run"]