import { Component, Input, OnInit } from '@angular/core';
import { User } from '../models/user.model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent implements OnInit {

  @Input()
  username!: string;

  user: User = new User();
  chronologicalHistory: Object[] = []
  chronologicalHistoryFiltered: Object[] = []
  nbPosts: number = 0;
  nbComments: number = 0;
  nbLikes: number = 0;
  nbDislikes: number = 0;

  constructor(private userService: UserService) {
    userService.getUser(this.username).subscribe(
      (response) => {
        this.user = response;
      },
      (error) => {
        console.error(error);
      }
    );
  }

  initUserValues(user: User): void {
    this.user = user;

    // TODO Récupérer l'historique :
    // La liste des posts
    this.nbPosts = 0;
    // La liste des comments
    this.nbComments = 0;
    // La liste des votes
    this.nbLikes = 0;
    this.nbDislikes = 0;

    // TODO Remplier chronologicalHistory avec ces éléments et trier chronologiquement
    this.chronologicalHistory = [];

    // TODO Filtrer l'historique avec seulement les éléments désirés
    this.chronologicalHistoryFiltered = [];
  }

  ngOnInit(): void {
  }

}
