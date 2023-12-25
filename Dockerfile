FROM timbru31/java-node:11-jdk-erbium

# update apt 
RUN rm /etc/apt/sources.list.d/yarn.list && apt update

# install maven
RUN apt install -y maven

# update node
RUN npm install -g n && n 16

# update to npm
RUN npm install -g npm@8.19.4

# "COPY" all files from the current directory over to Docker.
COPY . /app
WORKDIR /app

# build backend
RUN cd backend && mvn clean package -DskipTests
# build frontend (static)
RUN cd frontend && npm install && npm run build
# run server
WORKDIR "/app/backend"
CMD [ "./run"]