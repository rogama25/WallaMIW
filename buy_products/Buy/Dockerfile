#See https://aka.ms/containerfastmode to understand how Visual Studio uses this Dockerfile to build your images for faster debugging.

FROM mcr.microsoft.com/dotnet/aspnet:6.0 AS base
WORKDIR /app
EXPOSE 80

FROM mcr.microsoft.com/dotnet/sdk:6.0 AS build
WORKDIR /src
COPY ["Buy.csproj", "."]
RUN dotnet restore "./Buy.csproj"
COPY . .
WORKDIR "/src/."
RUN dotnet build "Buy.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "Buy.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
COPY wait-for .
RUN apt-get -q update && apt-get -qy install netcat wget
ENTRYPOINT ["./wait-for", "mongo:27017", "--", "dotnet", "Buy.dll"]