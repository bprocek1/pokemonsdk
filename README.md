# **A simple SDK for Pokemon Consumption API** 

Pokemonsdk makes it easy to communicate with the public pokemon consumption APIs.

**Getting Started**

Prerequisite: Install Maven and JDK

* Clone the repo to a project folder on your local workspace.
* Navigate to the project folder.
* Build the project: `mvn clean compile assembly:single`

**Integration Testing**
* Test generations endpoint:

`java -cp target/pokemonsdk-1.0-SNAPSHOT-jar-with-dependencies.jar com.bprocek1.pokemonsdk.app.GenerationsApp <idOrName>`
* Test: pokemon endpoint:

`java -cp target/pokemonsdk-1.0-SNAPSHOT-jar-with-dependencies.jar com.bprocek1.pokemonsdk.app.PokemonApp <idOrName>`

**Local Unit Testing**

* Test: `mvn test`

**Limitations**
* Response payloads are not being fully populated.
* Much more...