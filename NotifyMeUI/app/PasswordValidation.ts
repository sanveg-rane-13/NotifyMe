import {AbstractControl} from '@angular/forms';

export function passwordValidator(c:AbstractControl):{[key:string]:boolean}|null{

    let pwd=c.get('password').value;
    let confirmPassword=c.get('confirmPassword').value;

    if(pwd!==confirmPassword){
        c.get('confirmPassword').setErrors({match:true})
        console.log(false)
        return {'match':true}
    }

    return null;
}