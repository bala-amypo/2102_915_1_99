
Explorer
Workspace
demo
.mvn
src/main
java/com/example/demo
config
controller
AssetController.java
AssetDisposalController.java
AssetLifecycleEventController.java
AuthController.java
DepreciationRuleController.java
RootController.java
VendorController.java
dto
entity
exception
repository
service
util
DemoApplication.java
TestResultListener.java
resources
target
.gitattributes
.gitignore
mvnw
mvnw.cmd
pom.xml
Outline
Timeline
AssetController.java
demo
src
main
java
com
example
demo
controller
AssetController.java
123456789101112131415161718192021222324252627282930
package com.example.demo.controller;import com.example.demo.entity.Asset;import com.example.demo.service.AssetService;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;import java.util.List;@RestContpackage com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    
    private final AssetService assetService;
    
    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }
    
    @PostMapping("/{vendorId}/{ruleId}")
    public ResponseEntity<Asset> createAsset(@PathVariable Long vendorId, 
                                           @PathVariable Long ruleId, 
                                           @RequestBody Asset asset) {
        Asset created = assetService.createAsset(vendorId, ruleId, asset);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Asset>> getAssetsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(assetService.getAssetsByStatus(status));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAsset(@PathVariable Long id) {
        return ResponseEntity.ok(assetService.getAsset(id));
    }
}