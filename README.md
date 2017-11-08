# LeJOS EV3 Controller

Implementation of different movement:
- Line following
  - Forward & search: Move forward while on the line, then scan for the path
  - Right / Left Movement: Move to the left while on the line, to the right otherwise. Go stright by using an addapted acceleration on Motors
- Speed manager / Leader - follower
  - All or nothing: Stop the vehicle when too close
  - One point speed: Keep a linear speed based on distance
  - Two points: Adjust speed and try to reduce oscilations between two linear function 
  - Network managed: Send speed requests to the leader to adjust follower speed
