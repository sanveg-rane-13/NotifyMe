import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product-service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router'


@Component({
    selector: 'app-add-product',
    templateUrl: './add-product.component.html',
    styleUrls: ['./add-product.component.css'],

})
export class AddProductComponent implements OnInit {
    toggle: number = 1;
    productForm: FormGroup;
    urlResponse: any;
    product: any =
    {
        "userEmail": "",
        "url": "",
        "xPath": "",
        "productName": "",
        "currentPrice": 123,
        "userCriteria": 234

    }


    constructor(
        private router: Router,
        private productService: ProductService,
        private fb: FormBuilder) { }


    ngOnInit() {
        window.addEventListener("message",
            function (e) {
                console.log(e.origin);
                console.log(e.data);
                localStorage.setItem("xpath", e.data[0]);
                localStorage.setItem("xpath_value", e.data[1]);
            },
            false);
            console.log(this.getNumber("RS. 99900"));
        this.productForm = this.fb.group(
            {
                url: ['', Validators.required],
                productName: [''],
                price: [''],
                xpath: [''],
                criteria: ['', Validators.required]
            }
        );
    }
    addProduct() {

        if (this.productForm.valid) {
            this.product.userEmail = sessionStorage.getItem('email');
            this.product.url = this.productForm.value.url;
            this.product.userCriteria = this.getNumber(this.productForm.value.criteria);
            this.productService.addProduct(this.product)
            .toPromise().then(resp=>this.productForm.reset());
            //subscribe(resp => this.productForm.reset());
   
        }
       }
    getNumber(id: any) {
        if (typeof (id) == typeof ("smit")) {
            id = id.trim();
            id = id.replace(/\D+/g, "")
        }
        id = parseFloat(id.replace(",", ""));
        console.log(id);
        return id;
    }

    setVal(ids: any) {
        var val = localStorage.xpath_value;
        if (ids == 'xPath'){ 
            
            this.product.xPath = localStorage.xpath;val=localStorage.xpath;
          //console.log(localStorage.xpath);console.log(this.product.xPath);
        }
        if (ids == 'currentPrice'){ 
            this.product.currentPrice = this.getNumber(val); val=this.getNumber(val); }
        if(ids=='productName'){
            this.product.productName=val;
        }
        (<HTMLInputElement>document.getElementById(ids)).value = val;
         
        console.log(this.product);
    }
    toggleSearch() {
        this.toggle = this.toggle == 0 ? 1 : 0;
        return this.toggle;
    }
    UserAction(url) {
        var testIframe = document.getElementById('test');
        var iframe = <HTMLIFrameElement>testIframe;

        console.log(iframe);
        iframe.src = 'http://10.1.16.69:8080/searchProduct?url=' + url;


    }
     load_home() {

       var y = (<HTMLInputElement>document.getElementById('URL')).value;
       console.log(y);
       var response = "could not load :(";
       if (y) {

            this.UserAction(y);
        }
     }
    setFormValues(product) {
        this.productForm.setValue({
            url: product.url,
            productName: product.productName,
            price: product.currentPrice,
            xpath: product.xPath,
            criteria: product.currentPrice

        });
        console.log(this.productForm.value);

    }

    public getProductDetail(product: any) {
        console.log(product);
        this.product = product;
        this.setFormValues(product);
        this.toggleSearch();

    }

}
