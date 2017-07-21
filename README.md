***PENDING***

# What's New in Java 9
## Cleveland Java Meetup - August 19, 2017

## Demo

### Code

### JShell

## Slides

### SLIDE: What's New in Java 9
It's more than just another version
It is a significant change not only to the language, but to the JRE/JDK.

### SLIDE: So, what is new?

To answer that question, 
* Go through a condensed version of the official features list
* Drill-down on modules - ***THE Key feature***
* Demo a bit of code.

### SLIDE: Key Changes

It's a rather longer list, so let's take in sections.

Version-String Schema
* $MAJOR marks the major version Oracle is planning to release every two to three years.
* $MINOR marks the smaller releases for bug fixes and other details that follow regularly in between – it resets to zero when a new major version is released.
* $SECURITY is really interesting – it gets bumped with every release that “contains critical fixes including those necessary to improve security” and is not reset when $MINOR increases.

A key change becuase of build systems and version checks in code?

There's lots more to say aboutJava Platform Module System, but let's continue down the list

### SLIDE: New tools, improved tools
JShell
Java goes REPL
Of course, there's a demo
jlink
Assemble module sets into custom runtime images
jar
Create multiple Java-version-specific class files in a single JAR 
jcmd 
New commands to print class and method info, and UTF8 strings
java and javac
Validation of numeric JVM command-line flags such as memory settings
--release to avoid accidental use of APIs (enhances -source and -target) 

JVM performance
Garbage First (G1) is default GC 
Optimized for reduced latency over higher throughput
Deprecated Concurrent Mark and Sweep GC 
Replaced by G1
Removes Java 8 garbage collection combinations
DefNew + CMS, ParNew + SerialOld, and Incremental CMS
Unified JVM logging for all components including GC


Core libraries goodness
Process API offers better control of Process IDs
Compacted Strings now use byte arrays instead of char arrays
Added XML Catelogs
Convenience factories for collections: List.of(), Map.of(), Set.of()
Variable Handles replace Unsafe memory ordering fencing
Enhanced @Deprecation, now with forRemoval and since flags
Spin-Wait hints
Stack Walking API: easy filtering and lazy access of stack traces

There's always a misc
New security features
Small language improvements, private interface methods
JavaDocs module aware, simplified Doclet API
Java 9 installer enhancements
Nashorn supports EMCAScript 6 (selected features)
Client-side: Multi-resolution images, HiDPI on Windows and Linux, GTK 3
Supports Unicode 8.0, internationalization tweaks for XML and property files


Modules
What is modularity?
Managing and reducing complexity, especially at scale
Millions of lines of code
Dependencies across several dozen shared libraries
Strong encapsulation / Well-defined Interfaces / Explicit dependencies
Pre-Java 9 Modularity
JAR: grouping classes
Public gone wild
No explicit dependencies, you learn you made a mistake at runtime
Classpath
Destroys the grouping of JARs, all classes in the same flat list
No explicit version control, first loaded is the winner



Modular JRE/JDK
Time for an upgrade
Twenty year old, gigantic, monolithic runtime
Everything is present regardless of need. (When was the last time you used AWT, or CORBA?)
Unencapsulated internal APIs (sun.* package, Unsafe())
Java 8: introduced compact profiles. A start, but ….
Java 9: Project Jigsaw
Runtime source reorganized to support modularization
90+ modules with clearly defined interfaces and dependencies beginning with java.base
Encapsulated internal APIs
Custom runtime images (Think small, and IoT.)
Java 8 compact  profiles
. You have to move up to the next level, even if you only need one of its classes
. compact1 Smallest profile with Java core classes and logging and scripting APIs.
. compact2 Extends compact1 with XML, JDBC and RMI APIs.
. compact3 Extends compact2 with security and management APIs.



Modularity building block
module-folder/
module classes and resources
module-info.java
			module module.name {
				exports  package [to target-package];
				requires [transitive] module.name;
			}
Strong encapsulation: All packages in a module are private to the module
Exported interface: Packages must be explicitly exported to be public
Declared dependencies: Specifies required modules
It's all about readability



So, how is the wonderful trick performed?
	The module-folder and the module-info.java file
	The module path

Readability
.In order to access exported packages from another module, you must require it. In other words, you must be able to "read" the other module.  

.Transitive requires allows you to make something you require in your module, transitively available to another module that is "reading" (i.e. requires) your module.  For instance, perhaps you require java.logging in your module so you can do some logging. If you want to make the instance of the logger through a method that you export, you potential put a burden on the other module to require java.logging itself.  However, you declare a transitive requires in your module descriptor, and the other modules implicitly requires it.

Qualified Exports
.You may want to export a package to a specific module only.  Use the to clause.

Goodbye! classpath. Hello! module path.
The classpath has been replaced with the module path. 
Compiler and runtime use the module path to resolve exports and requires
"Computing the transitive closure of the dependency graph"* (a.k.a. module resolution)
Start with a single root module, add to the dependency graph
Add each new, non-duplicating requires module to the dependency graph
Repeat Step 2 for each module added in Step 2 
Old code in the land of modules: a pre-migration story
The classpath hasn't totally disappeared, just ignored unless needed
Any classes on the classpath are loaded into the unnamed module
The unnamed module automatically reads all other modules




* Java 9 Modularity, Bakker and Mak

Demo
Explain Java 8 Elevator code
. Add instance of Car to Building for demo purposes to show you can access any public class.
Car car = new Car(new Shaft(1, 20));

Translating to modules.
. We want to prevent Building from accessing anything by elevator.
. In for a penny, in for a pound. Once you enter the world of modules, everything becomes a module.
. Each module needs its own module folder, so class files get moved around and re-packaged

….Building becomes one module
src/
   building/
      com/techelevator/building/   <<== Create new package
         Building.java
   Module-info.java
      Requires elevator

...and Elevator becomes another module
   elevator/
      com/techelevator/elevator/             << == Create new package 
         Elevator.java
      com/techelevator/elevator/drive/    <<== Create new package
         Direction.java
         Idler.java
         Motor.java
      com/techelevator/elevator/shaft/     <<== Create new package
         Car.java
         Shaft.java
   Module-info.java
      Exports com.techelevator.elevator

To compile
javac -d bin --module-source-path  src  $(find . -name '*.java')

To run
java --module-path bin --module building/com.techelevator.building.Building



References
Official feature list from Oracle
https://docs.oracle.com/javase/9/migrate/toc.htm#JSMIG-GUID-7744EF96-5899-4FB2-B34E-86D49B2E89B6
Java 9 Modularity, 1st ed.
Sander Mak, Paul Bakker, O'Reilly Media, Inc., 2017.
Java 9 for Programmers, 4th ed.
Paul Deitel, Harvey Deitel, Prentice Hall, 2017.
Modular Programming in Java 9
Koushik Kothagal, Packt Publishing, 2017.
Java 9 with JShell
Gastón C. Hillar, Packt Publishing, 2017.

