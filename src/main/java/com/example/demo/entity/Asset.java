coder@0f9817395869:~/Workspace/demo$ mvn clean install
[INFO] Scanning for projects...
[INFO] 
[INFO] --------------------------< com.example:demo >--------------------------
[INFO] Building demo 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:3.3.2:clean (default-clean) @ demo ---
[INFO] Deleting /home/coder/Workspace/demo/target
[INFO] 
[INFO] --- maven-resources-plugin:3.3.1:resources (default-resources) @ demo ---
[INFO] Copying 1 resource from src/main/resources to target/classes
[INFO] Copying 0 resource from src/main/resources to target/classes
[INFO] 
[INFO] --- maven-compiler-plugin:3.13.0:compile (default-compile) @ demo ---
[INFO] Recompiling the module because of changed source code.
[INFO] Compiling 45 source files with javac [debug parameters release 17] to target/classes
[INFO] /home/coder/Workspace/demo/src/main/java/com/example/demo/config/JwtFilter.java: /home/coder/Workspace/demo/src/main/java/com/example/demo/config/JwtFilter.java uses unchecked or unsafe operations.
[INFO] /home/coder/Workspace/demo/src/main/java/com/example/demo/config/JwtFilter.java: Recompile with -Xlint:unchecked for details.
[INFO] -------------------------------------------------------------
[ERROR] COMPILATION ERROR : 
[INFO] -------------------------------------------------------------
[ERROR] /home/coder/Workspace/demo/src/main/java/com/example/demo/controller/AssetController.java:[37,14] cannot find symbol
  symbol:   method setStatus(java.lang.String)
  location: variable asset of type com.example.demo.entity.Asset
[ERROR] /home/coder/Workspace/demo/src/main/java/com/example/demo/controller/AssetDisposalController.java:[35,17] cannot find symbol
  symbol:   method setCreatedAt(java.time.LocalDateTime)
  location: variable disposal of type com.example.demo.entity.AssetDisposal
[ERROR] /home/coder/Workspace/demo/src/main/java/com/example/demo/controller/AssetDisposalController.java:[48,28] cannot find symbol
  symbol:   method setStatus(java.lang.String)
  location: class com.example.demo.entity.Asset
[ERROR] /home/coder/Workspace/demo/src/main/java/com/example/demo/controller/AssetLifecycleEventController.java:[31,14] cannot find symbol
  symbol:   method setLoggedAt(java.time.LocalDateTime)
  location: variable event of type com.example.demo.entity.AssetLifecycleEvent
[ERROR] /home/coder/Workspace/demo/src/main/java/com/example/demo/controller/AuthController.java:[39,13] cannot find symbol
  symbol:   method getRoles()
  location: variable user of type com.example.demo.entity.User
[ERROR] /home/coder/Workspace/demo/src/main/java/com/example/demo/controller/AuthController.java:[42,48] cannot find symbol
  symbol:   method getEmail()
  location: variable user of type com.example.demo.entity.User
[ERROR] /home/coder/Workspace/demo/src/main/java/com/example/demo/controller/AuthController.java:[44,26] cannot find symbol
  symbol:   method getEmail()
  location: variable user of type com.example.demo.entity.User
[INFO] 7 errors 
[INFO] -------------------------------------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  13.599 s
[INFO] Finished at: 2025-12-20T15:59:57Z
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.13.0:compile (default-compile) on project demo: Compilation failure: Compilation failure: 
[ERROR] /home/coder/Workspace/demo/src/main/java/com/example/demo/controller/AssetController.java:[37,14] cannot find symbol
[ERROR]   symbol:   method setStatus(java.lang.String)
[ERROR]   location: variable asset of type com.example.demo.entity.Asset
[ERROR] /home/coder/Workspace/demo/src/main/java/com/example/demo/controller/AssetDisposalController.java:[35,17] cannot find symbol
[ERROR]   symbol:   method setCreatedAt(java.time.LocalDateTime)
[ERROR]   location: variable disposal of type com.example.demo.entity.AssetDisposal
[ERROR] /home/coder/Workspace/demo/src/main/java/com/example/demo/controller/AssetDisposalController.java:[48,28] cannot find symbol
[ERROR]   symbol:   method setStatus(java.lang.String)
[ERROR]   location: class com.example.demo.entity.Asset
[ERROR] /home/coder/Workspace/demo/src/main/java/com/example/demo/controller/AssetLifecycleEventController.java:[31,14] cannot find symbol
[ERROR]   symbol:   method setLoggedAt(java.time.LocalDateTime)
[ERROR]   location: variable event of type com.example.demo.entity.AssetLifecycleEvent
[ERROR] /home/coder/Workspace/demo/src/main/java/com/example/demo/controller/AuthController.java:[39,13] cannot find symbol
[ERROR]   symbol:   method getRoles()
[ERROR]   location: variable user of type com.example.demo.entity.User
[ERROR] /home/coder/Workspace/demo/src/main/java/com/example/demo/controller/AuthController.java:[42,48] cannot find symbol
[ERROR]   symbol:   method getEmail()
[ERROR]   location: variable user of type com.example.demo.entity.User
[ERROR] /home/coder/Workspace/demo/src/main/java/com/example/demo/controller/AuthController.java:[44,26] cannot find symbol
[ERROR]   symbol:   method getEmail()
[ERROR]   location: variable user of type com.example.demo.entity.User
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
coder@0f9817395869:~/Workspace/demo$ 