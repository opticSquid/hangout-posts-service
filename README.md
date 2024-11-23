# Hangout Posts Service

## Introduction

This service serves a pivotal role to create the feed of posts for the users of Hangout.

## Current Capabilities

This service serves this functions currently:
- get the posts to create the feed for the user
- upload new posts
- in case the post contains photos and videos upload them to shared path and create event in kafka topic to let the `hangout-storage-service` know that new files are to be processed
- Integrate with grafana stack directly to correlate observability data with other services.
- Integrated with Open API 3 api definition specs

 ## Planned Features

- Send the observability data to otel collector instead of specific to any service provider
- Add a `place-name` parameter to the post. This field would contain the profile of the business about which the current post is being done in case the business is registerted with Hangout.
- Add a `geo-location` and `place-name` parameter to the posts to contain the location of the business about which the post is being done in case the business is not registered with Hangout.
- While sending the posts to create user feed, filter the posts based on user location, their preferences and their budget range.