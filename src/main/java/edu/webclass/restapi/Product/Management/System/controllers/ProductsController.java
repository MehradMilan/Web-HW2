package edu.webclass.restapi.Product.Management.System.controllers;

import edu.webclass.restapi.Product.Management.System.models.Product;
import edu.webclass.restapi.Product.Management.System.models.dto.ProductDto;
import edu.webclass.restapi.Product.Management.System.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public List<ProductDto> listAllProducts(){
        return productService.findAllProducts().stream().map(product -> new ProductDto(product)).toList();
    }

    @PostMapping("/add")
    public boolean addProduct(@RequestHeader("name") String title,@RequestHeader String brand,@RequestHeader int price){
        return productService.addProduct(title,brand,price);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProduct(@PathVariable String id){
        Product product = productService.findProductById(id);
        if(product == null){
            return new ResponseEntity<>("Product not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.toString(), HttpStatus.OK);
    }
}
