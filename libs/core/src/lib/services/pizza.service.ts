import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CreatePizzaInput, Pizza, UpdatePizzaInput } from '@pizzaperfect/data';
import { Observable } from 'rxjs';

@Injectable({
	providedIn: 'root',
})
export class PizzaService {
	constructor(private http: HttpClient) {}

	public getPizzas(): Observable<Pizza[]> {
		return this.http.get<Pizza[]>(`/api/pizza-orders`);
	}

	public getPizza(id: number): Observable<Pizza> {
		return this.http.get<Pizza>(`/api/pizza-orders/${id}`);
	}

	public createPizza(pizza: CreatePizzaInput): Observable<Pizza> {
		return this.http.post<Pizza>(`/api/pizza-orders`, pizza);
	}

	public updatePizza(id: number, pizza: UpdatePizzaInput): Observable<Pizza> {
		return this.http.put<Pizza>(`/api/pizza-orders/${id}`, pizza);
	}
}
