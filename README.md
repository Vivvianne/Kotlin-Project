# Ilara Junior Software Engineering Coding Challenge

Welcome to Ilara Health's junior software engineering challenge! This project is designed to test your skills in a real-world scenario. We're asking you to write some Kotlin code as part of the Spring Framework, but we don't expect you to have any experience in either. In fact, we're worried this might be too easy if you do! We're testing your ability to pick up new languages, frameworks and concepts, and are also looking for general coding practices. As always we're interested in your process as much as the final result. 

## So what am I building?
You'll be building a basic healthcare microservice in Spring Boot. We've already written a bunch of boilerplate code, and given you examples in the code to base your solution off of. I've also included a number of resources here to get your started.

### Ok, let's get more specific
Ilara Health's product is built on a number of modules, and one of those is management/administration. The full module defines everyone that works at an organization, and their relationships with each other. For this challenge, you'll only be working with Practitioners (health staff) and Organizations (a medical facility). **Your task is to implement the following functionality:**
1. Map a PractitionerDto to Practitioner object using mapstruct (Dto stands for Data Transfer Object)
2. Implement the create practitioner method
3. Implement the search method for Organizations. Organizations should be searchable by name, or, if no arguments are included, search should return all organizations
4. Write a test to make sure your mapping is working

The code has been marked with TODO tags where these methods need to be implemented

### Great, how do I do this?

Good question! You can approach this however you want, but here's what we recommend:
1. Start by forking and cloning the project **Please fork and don't work off of this repo** we need to send this to all of our candidates. Poke around and see what you can figure out. You'll also need to download IntelliJ.
2. Understand [Kotlin Basics](https://spring.io/projects/spring-boot) if you've never worked with it before. It's very similar to Java, but with explicit null declarations.
3. Try and understand how the [Spring Boot Framework](https://spring.io/projects/spring-boot) works. I've included some explainers and links below, and Google is your friend! 
4. Understand [MapStruct](https://mapstruct.org/) and how it works. I've also included a more detailed explainer blow. The [reference guide](https://mapstruct.org/documentation/stable/reference/html/) is a great resource
5. Run the project and try sending some requests with Postman. To run or debug the project, go to ManagementModuleApplication.kt in IntelliJ and click the green arrow beside fun main()
6. Take a quick look at the FHIR implementation for [Practitioner](https://www.hl7.org/fhir/practitioner.html) and [Organization](https://www.hl7.org/fhir/organization.html). You'll be converting from the data transfer objects, into these models. The library we're using to implement FHIR is [HapiFHIR](https://hapifhir.io/hapi-fhir/). You'll need to read the documentation to implement [create](https://hapifhir.io/hapi-fhir/docs/client/generic_client.html#create-type) and [search](https://hapifhir.io/hapi-fhir/docs/client/generic_client.html#search). You **do not** need to fully understand the FHIR protocol to complete this assignment. Just use the JSON objects linked here. The HapiFHIR implementation gives you essentially perfect POJO replicas of these objects.
7. Read the persistence section to understand a bit about where you'll be sending data.

### What should I do when I'm done?
Send us a link to your forked repo! 

### How will I be evaluated?
We'll be looking at a few things. The top things we're looking for are:
1. Completeness. Finishing everything, as well as accounting for edge cases
2. Code quality. Was your code as concise as possible? Did you document anything non-standard? Are your variable, method and class names clear? Are you following conventions?
3. Efficiency. You're not making any unnecessary requests to the FHIR server or over-engineering your solution.  

We'll also be looking at:
1. Following git best practices. Commit early, commit often, and make sure your commits are logical.

## Guides and Resource 

### Spring Boot

Spring Boot is an implementation of the Spring Framework that makes a bunch of assumptions about how applications should be developed. Spring Boot is opinionated, whereas the general Spring Framework isn't. This is an important distinction, because when you're doing research it's easy to go down a hole looking for a solution and then find out you were reading about Spring instead of Spring Boot the whole time.

Spring appears to operate by magic when you first start with it, but all of that magic can be explained by a concept called [Inversion of Control](https://www.baeldung.com/inversion-control-and-dependency-injection-in-spring) (IoC). Inversion of Control means that you tell Spring where to find and create dependencies, and then it injects those dependencies into your classes. 

For example, the `PractitionerMapper.kt` class that you need to implement is annotated with `@Mapper(componentModel = "spring")`. This tells Spring that there is a component called PractitionerMapper, which can be injected as a dependency.For the purposes of this evaluation, you only need to worry about constructor injection. If you look at `PractitionerService.kt`, you'll see that one of the constructor vars is of type `PractitionerMapper`. When `PractitionerService` is loaded, Spring will look for a component of  type `PractitionerMapper`. It will find the `PractitionerMapper` class defined in the Mappers folder, instantiate a new object, and pass it to the `PractitionerService` object. 


`@Mapper` is a MapStruct specific annotation. In general, to define components you would use one of `@Component`, `@Service`, or `@RestController`. All of these will make those classes available to be injected. `HapiClient` is defined using a factory method, and the `@Bean` annotation, which has a similar effect.

### Mapstruct

MapStruct was built to make it easy to map between Plain Old Java (and Kotlin) Objects (POJOs). At it's [most basic](https://mapstruct.org/documentation/stable/reference/html/#basic-mappings), you really don't have to do much to get it to work (it's worth taking a look at that link to get the basic idea). When you build the application, MapStruct looks at all of the abstract classes and interfaces with the `@Mapping` annotation and generates a class that implements/subclasses the interface/abstract class that you defined. 

You can view the generated classes at *build/generated/source/kapt/main/com/ilarahealth/management_module/mappers* and use that to check your work. If you annotated your methods correctly, you should see code that maps each member in your source class, to the correct member in your target class.

The mapping you'll have to do for this challenge is a bit more complicated. You should understand how the OrganizationMapper, which has already been defined works. Specifically, [understand](https://mapstruct.org/documentation/stable/reference/html/#invoking-other-mappers)  how the `uses` keyword works.

Keep in mind that MapStruct is pretty smart. If you define a method with the correct source (method parameter) and target (return type), it will use that method instead of trying to do the conversion itself.

### What the f**k is FHIR?

FHIR stands for Fast Healthcare Interoperability Resources and was created so that healthcare systems can exchange data easily using their protocol. Their JSON schema for healthcare systems is really good, so we're using it for pretty much everything we're doing at Ilara. 

FHIR, however, is just a protocol. We're using Hapi's complete implementation of the FHIR standard. Beyond reading this, their documentation on [creating](https://hapifhir.io/hapi-fhir/docs/client/generic_client.html#create-type) and [searching](https://hapifhir.io/hapi-fhir/docs/client/generic_client.html#search) for resources, and looking at the [Practitioner](https://www.hl7.org/fhir/practitioner.html) and [Organization](https://www.hl7.org/fhir/organization.html) models, you shouldn't need to know anything else about the standard.

The only additional thing you need to know about FHIR is that every object is a [Resource](https://www.hl7.org/fhir/resource.html), which is important because the code for create and search will both work with any Resource (so you could use the same code for a Practitioner and an Organization)

#### Persistence

The app you're developing has been configured as a client of a FHIR server that we've set up on our servers. You can think of it as an external DB that you can perform CRUD operations on, and search/query. The configuration is already done for you, but you'll need to access the `IGenericClient` to perform queries and create resources, and you might have to use `FhirContext` as well. You can get both of these objects from `HapiClient`. There are a few examples of how `HapiClient` is injected into classes, and used for operations, and Hapi has a pretty good [guide](https://hapifhir.io/hapi-fhir/docs/client/generic_client.html).