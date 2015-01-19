# Tinder Java API [![Build Status](https://travis-ci.org/aldoborrero/tinder-java-api.svg)](https://travis-ci.org/aldoborrero/tinder-java-api)

![](assets/tinder-flame.png)

A Java wrapper for Tinder API implemented using [Retrofit](http://square.github.io/retrofit) + [OkHttp](http://square.github.io/okhttp/).

This project is currently being used in: [TinderJs for Android](https://github.com/aldoborrero/tinderjs-android).

## Usage

**NOTE:** Keep in mind that the project is in its infancy so for that reason is not uploaded yet to Maven Central. So you'll have to compile it by yourself till the API gets stable.

Add the following dependency to your Gradle project:

```
compile 'com.aldoborrero:tinder-java:x.x.x'
```

or your Maven project:

```
<dependency>
<groupId>com.aldoborrero</groupId>
<artifactId>tinder-java</artifactId>
<version>x.x.x</version>
</dependency>
```

** Note: ** Adjust `X.X.X` to a corresponding version.

## TODO

* Intercept auth token
* Finish basic tests
* Implement correctly mock-api subproject

## Contributions

Feel free to contribute to this project if you feel something could be improved or fixed. Only thing to notice is that commits should go to `develop` branch as master is for stable builds!

## <a name="about"></a> About

You can find me on:

- [YouTube Channel](https://www.youtube.com/user/aldoborrero)
- Twitter: [@aldoborrero](http://twitter.com/aldoborrero)
- Google+: [+aldoborrero](http://plus.google.com/+aldoborrero)

## <a name="acknowledgements"></a> Acknowledgements

This project has a big inspiration on [`tmdb-java`](https://github.com/UweTrottmann/tmdb-java) project created by [@UweTrottmann](https://github.com/UweTrottmann).

## License

Copyright 2015 Aldo Borrero <aldo@aldoborrero.com>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
