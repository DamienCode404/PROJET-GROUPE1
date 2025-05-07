# Dockerfile "multi-staging"
# Des étapes intermédiaires qui ne se retrouveront pas dans l'image Docker finale
# > On peut faire des images SANS les dépendances, les code-source, etc...

# Le "AS" permet de nommer le container intermédiaire du build, pour y faire référence plus tard dans le Dockerfile
FROM node:lts-bullseye AS build

WORKDIR /app

# Copier les fichiers package.json & package-lock.json
COPY package*.json .

# Avec ces fichiers, on peut lancer l'installation des dépendances
RUN npm install

# On copie la suite : les sources et la config etc.
COPY . .

# Builder l'application
RUN ./node_modules/.bin/ng build


# Passer au container final, qui contiendra nginx ET les fichiers HTML, CSS et JS de l'application ANGULAR
FROM nginx:latest

WORKDIR /app

COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copier du container de fabrication "build" les fichiers dans la destination NGINX
COPY --from=build /app/dist/refuge-front/browser/* /usr/share/nginx/html/


EXPOSE 80
