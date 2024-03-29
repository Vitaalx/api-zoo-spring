# API ZOO using Spring Boot v2.6.8

#### ***This API is using to manage a Zoo !***
#### ***Swagger generated ✔***
#### ***Unit Test ✔***

### <u>**Language used**</u> :

<div style="display: flex; align-items: center;">
    <img align="left" alt="Java" width="96px" title="Java" src="https://i.imgur.com/i6e6h7k.png" />
    <img style="margin-left: 3%;" width="96px" alt="Spring Boot" title="Spring Boot" src="https://i.imgur.com/tE8gEkp.png" />
</div>

## All animals
<span style="color:#61affe">**@GET**</span>
>`/api-zoo/animals` - Returns all Animals
> > <u>**Parameters**</u> </br></br>
> > No parameters

>API Responses
> ```
> [
>     {
>         "id": 0,
>         "name": "Milou",
>         "age": 6,
>         "type": "Mammifères",
>         "nomEspece": "Chien",
>         "sex": "Male"
>     }
> ]
> ```
| Code     | Description  |
|----------|:------------:|
| 200      | **Success**  |

## All Animals by Type
<span style="color:#61affe">**@GET**</span>
>`/api-zoo/animals/byType/{type}` - Returns all Animals filter by **type** in path
> > <u>**Parameters**</u> </br></br>
> > **animalType*** : Type existing in the zoo <br/>`String` </br>*(path)*

>API Responses
> ```
> [
>     {
>         "id": 0,
>         "name": "Milou",
>         "age": 6,
>         "type": "Type (in path)",
>         "nomEspece": "Chien",
>         "sex": "Male"
>     }
> ]
> ```

| Code     |  Description   |
|----------|:--------------:|
| 200      |  **Success**   |

---

| Code |            Description             |
|------|:----------------------------------:|
| 400  | **Bad Request**<br/>(Type invalid) |

| Code |                              Description                              |
|------|:---------------------------------------------------------------------:|
| 404  | **Not Found** </br>No animal with this ***type***<br/>(Type existing) |

## Animal by id
<span style="color:#61affe">**@GET**</span>
>`/api-zoo/animal/byId/{id}` - Returns Animal identified by the **Id** in path
> > <u>**Parameters**</u> </br></br>
> > **idAnimal*** : Animal ID </br>`Integer` </br>*(path)*

>API Responses
> ```
> [
>     {
>         "id": idAnimal (in path),
>         "name": "Milou",
>         "age": 6,
>         "type": "Mammifères",
>         "nomEspece": "Chien",
>         "sex": "Male"
>     }
> ]
> ```

| Code     | Description  |
|----------|:------------:|
| 200      | **Success**  |

---

| Code |             Description             |
|------|:-----------------------------------:|
| 400  | **Bad Request**<br/>(Type invalid)  |

| Code |                   Description                   |
|------|:-----------------------------------------------:|
| 404  | **Not Found** </br>No animal with this ***id*** |

## Animal by Name
<span style="color:#61affe">**@GET**</span>
>`/api-zoo/animal/byName/{name}` - Returns Animal identified by the **Name** in path
> > <u>**Parameters**</u> </br></br>
> > **animalName*** : Name of the Animal </br>`String` </br> *(path)*

>API Responses
> ```
> [
>     {
>         "id": 4,
>         "name": "Name (in path)",
>         "age": 6,
>         "type": "Mammifères",
>         "nomEspece": "Chien",
>         "sex": "Male"
>     }
> ]
> > ```

| Code     | Description  |
|----------|:------------:|
| 200      | **Success**  |

---

| Code |                    Description                    |
|------|:-------------------------------------------------:|
| 404  | **Not Found** </br>No animal with this ***Name*** |

## Number of Animals in the Zoo
<span style="color:#61affe">**@GET**</span>
>`/api-zoo/animals/count` - Returns number of Animals in the zoo
> > <u>**Parameters**</u> </br></br>
> > No parameters

>API Responses
> ```
> Il y a ... animaux dans le Zoo
> ```

| Code     | Description  |
|----------|:------------:|
| 200      | **Success**  |

## Number of Animal types
<span style="color:#61affe">**@GET**</span>
>`/api-zoo/animals/count/byType` - Returns the number of animal types
> > <u>**Parameters**</u> </br></br>
> > No parameters

>API Responses
> ```
> Il y a ... types d'animaux
> ```

| Code     | Description  |
|----------|:------------:|
| 200      | **Success**  |

## Create Animal(s)
<span style="color:#49cc90">**@POST**</span>
>`/api-zoo/createAnimals` - Create Animal(s) with JSON Object
> > <u>**Parameters**</u> </br></br>
> > **requestAnimal*** : requestAnimal </br>
> > ```
> > [
> >   {
> >       "name": "Oscar",
> >       "age": 6,
> >       "type": "Mammifères",
> >       "nomEspece": "Chien",
> >       "sex": "Male"
> >   }
> > ]
> > ```
> > `object` (array) </br> *(body)*




>API Responses
> > Animal créé

| Code     | Description |
|----------|:-----------:|
| 200      | **Success** |

---

| Code |                Description                 |
|------|:------------------------------------------:|
| 401  | **Unauthorized**</br>Name already existing |

## Update Animal
<span style="color:#fca130">**@PUT**</span>
>`/api-zoo/animal/{id}` - Update Animal with ID.
> > <u>**Parameters**</u> </br></br>
> >  **idAnimal***  : Animal ID </br> `Integer` </br> *(path)* </br></br>
> > **requestAnimal*** : requestAnimal </br>
> > ```
> > [
> >   {
> >       "age": 6,
> >       "type": "Mammifères",
> >       "nomEspece": "Chien",
> >       "sex": "Male"
> >   }
> > ]
> > ```
> > `object` (array) </br> *(body)*




>API Responses
> > OK

| Code     | Description |
|----------|:-----------:|
| 200      | **Success** |

---
> Tous les champs doivent être complétés ! (age, type, nomEspece et sex).

| Code |   Description   |
|------|:---------------:|
| 400  | **Bad Request** |

> Aucun animal n'est identifié par cet Id. Veuillez vérifier l'Id envoyé !.

| Code |  Description  |
|------|:-------------:|
| 404  | **Not Found** |

## Delete Animal
<span style="color:#f93e3e">**@DELETE**</span>
>`/api-zoo/animal/{id}` - Delete Animal with ID.
> > <u>**Parameters**</u> </br></br>
> >  **idAnimal***  : Animal ID </br> `Integer` </br> *(path)* </br>


>API Responses
> > OK

| Code     | Description |
|----------|:-----------:|
| 200      | **Success** |

---
> Tous les champs doivent être complétés ! (age, type, nomEspece et sex).

| Code |   Description   |
|------|:---------------:|
| 400  | **Bad Request** |

> Aucun animal n'est identifié par cet Id. Veuillez vérifier l'Id envoyé !.

| Code |  Description  |
|------|:-------------:|
| 404  | **Not Found** |














