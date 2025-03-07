import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { FileuploadService } from '../../fileupload.service';
import { City } from '../../model';
import { CityStore } from '../../store/city.store';

@Component({
  selector: 'app-upload',
  standalone: false,
  templateUrl: './upload.component.html',
  styleUrl: './upload.component.css'
})
export class UploadComponent implements OnInit{
  form!: FormGroup;
  dataUri! :string;
  blob!: Blob;
  private router = inject(Router);
  private fb = inject(FormBuilder);
  private fileUploadSvc = inject(FileuploadService);
  private cityStore = inject(CityStore);


  citiesList$ !: Observable<City[]>;
  selectedCity !: string;
  selectedCityName ?: string;
  
  ngOnInit(): void {
    this.createForm();
    this.loadCities();
  }

  loadCities(){
    this.citiesList$ = this.cityStore.cities$;
    this.cityStore.loadCities();
  }

  upload(){
    console.log(this.dataUri);
    if(!this.dataUri){
      return;
    }
    this.blob = this.dataURItoBlob(this.dataUri);
    const formValue = this.form.value;
    this.fileUploadSvc.upload(formValue, this.blob)
      .then((result) => {
        console.log(result);
        this.router.navigate(['/image', result.postId]);
      });
  }

  onFileChange(event: Event){
    console.log("onFileChange");
    const input = event.target as HTMLInputElement;
    if(input.files && input.files.length > 0){
      const file = input.files[0];
      console.log(file);
      const reader = new FileReader();
      reader.onload = () => {
        this.dataUri = reader.result as string;
      };
      reader.readAsDataURL(file);
    }

  }

  // akan datang !
  dataURItoBlob(dataURI: string): Blob{
    const [meta, base64Data] = dataURI.split(',');
    const mimeMatch = meta.match(/:(.*?);/);

    const mimeType = mimeMatch ? mimeMatch[1] : 'application/octet-stream';
    const byteString = atob(base64Data);
    const ab = new ArrayBuffer(byteString.length);
    const ia = new Uint8Array(ab);
    for(let i = 0; i < byteString.length; i++){
      ia[i] = byteString.charCodeAt(i);
    }
    return new Blob([ia], {type: mimeType});
  }

  createForm(){
    this.form = this.fb.group({
      comments: this.fb.control<String>('')
    });
  }
}