# Pattern Recognition Project

The project is intended to investigate a pattern recognition problem involving points and line segments.
Given a certain set of feature points, the elaborated algorithm is able to determine every lines that contains N or more points in the plane.

## Requirements

- Java JDK 17
- Apache Maven
- No Database installation is needed since the application uses an H2 in-memory database


## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/MattiaMorelli/pattern_recognition.git

2. Go to the project directory

3. Build the project using the maven command

    ```bash
   mvn clean install
   
## Run
1. To run the Spring Boot application:

    ```bash
   mvn spring-boot:run
   
2. The application listens on port 8080

## Exposed services
- POST /api/point: Add a point in the space
- GET /api/space:  Get all points in the space
- GET /api/lines/{n}: Get all line segments passing through at least N points
- DELETE /api/space: Remove all points from space

To access APIs documentation and test them, browse to http://localhost:8080/swagger-ui/index.html once the application is running. 

## Algorithm logic
The algorithm is a Java implementation that determines every line in the plane that contains N or more feature points.

The algorithm utilizes the concept of **slopes** and **intercepts** to identify lines formed by pairs of points and groups them together based on their properties.
1. **Point Class**: The Point class represents a 2D point on the plane, with integer x and y as coordinates.
2. **Line Class**: The Line class represents a line on the plane defined by its slope and intercept. It also maintains a list of points that lie on the line.
3. **findLinesWithNPoints Method**:
   - The findLinesWithNPoints method takes an integer N as input, representing the minimum number of points required on a line.
   - The method initializes an empty list of Line objects called result to store the lines that meet the criteria.
   - A nested hashmap called slopeMap is created to group lines with the same slope. The outer hashmap uses the slope as the key, and the inner hashmap uses the intercept as the key.
4. **Iterating Over Points**:
   - All feature points are retrieved from the data source.  
   - If there are no points or the number of points is less than N, the algorithm returns an empty result, as there can be no lines with N or more points.
5. **Finding Lines**:
   - The algorithm iterates over all pairs of points to calculate the slope and intercept of each line formed by the pair.
   - It uses the calculated slope and intercept to group the lines in the slopeMap.
   - The lines are added to the map with their slope and intercept as keys. If a line already exists with the same slope and intercept, it is retrieved, and the points forming the line are added to it.
6. **Extracting Valid Lines**:
   - After populating the slopeMap with lines, the algorithm iterates over the map to find lines that contain N or more points.
   - Lines that meet the criteria are added to the result list.

### Slope calculation
The slope of a line passing through two points (x1, y1) and (x2, y2) is given by **(y2 - y1) / (x2 - x1)**. 

However, if the two points have the same x-coordinate, the line is vertical, and the slope is infinite (positive infinity). In this case, the algorithm sets the slope to Double.POSITIVE_INFINITY.

### Intercept calculation
The intercept of a line with slope s passing through a point (x, y) is given by **y - s * x**.

If the line is vertical (slope is infinite), the x-coordinate of the point is used as the intercept.
