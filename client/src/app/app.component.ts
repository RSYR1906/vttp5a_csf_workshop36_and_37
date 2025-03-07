import { Component, OnInit, inject } from '@angular/core';
import { CitiesService } from './cities.service';
import { City } from './model';
import { CityStore } from './store/city.store';

@Component({
  selector: 'app-root',
  standalone: false,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  cities !: City[];
  private citiesSvc = inject(CitiesService);
  private citiesStore = inject(CityStore);

  async ngOnInit() {
    this.cities = await this.citiesSvc.getCities();
    this.cities.forEach((cityObj) => {
      console.log(cityObj)
      this.citiesStore.addNewCity(cityObj);
    });
  }
}