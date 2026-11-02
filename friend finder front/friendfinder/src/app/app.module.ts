import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HeaderComponent } from './componants/header/header.component';
import { FooterComponent } from './componants/footer/footer.component';
import { SignUpComponent } from './componants/sign-up/sign-up.component';
import { ContactComponent } from './componants/contact/contact.component';
import { UserhomeComponent } from './componants/userhome/userhome.component';
import { LeftBarComponent } from './componants/userhome/left-bar/left-bar.component';
import { RightBarComponent } from './componants/userhome/right-bar/right-bar.component';
import { PublishComponent } from './componants/userhome/publish/publish.component';
import { CoolImagesComponent } from './componants/userhome/cool-images/cool-images.component';
import { MainPageComponent } from './componants/userhome/main-page/main-page.component';
import { TimeLinesComponent } from './componants/time-lines/time-lines.component';
import { TimeLineComponent } from './componants/time-lines/time-line/time-line.component';
import { TimeAboutComponent } from './componants/time-lines/time-about/time-about.component';
import { TimeAlbumComponent } from './componants/time-lines/time-album/time-album.component';
import { TimeFriendsComponent } from './componants/time-lines/time-friends/time-friends.component';
import { TimeLineProfileComponent } from './componants/time-lines/time-line-profile/time-line-profile.component';
import { TimeLineDetailesComponent } from './componants/time-lines/time-line-detailes/time-line-detailes.component';
import {RouterModule, Routes} from '@angular/router';
import { LoginComponent } from './componants/login/login.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {InterceptorInterceptor} from './service/interceptor/interceptor.interceptor';
import {AuthGuard} from './service/guard/auth.guard';
import {NotAuthGuard} from './service/guard/not-auth.guard';
import {ProfileComponent} from './componants/profile/profile.component';
import { RequestsComponent } from './componants/requests/requests.component';
import { AboutComponent } from './componants/about/about.component';
import {FormsModule} from "@angular/forms";


const routes: Routes = [
  {path:'login', component: LoginComponent, canActivate:[NotAuthGuard]},
  {path: 'signup', component: SignUpComponent, canActivate:[NotAuthGuard]},
  {path: 'mainPage', component: MainPageComponent,canActivate:[AuthGuard]},
  {path: 'contact', component: ContactComponent,canActivate:[AuthGuard]},
  {path: 'timeline', component: TimeLineComponent,canActivate:[AuthGuard]},
  {path: 'requests', component: RequestsComponent,canActivate:[AuthGuard]},
  {path: 'profile', component: ProfileComponent,canActivate:[AuthGuard]},
  {path: 'left', component: LeftBarComponent,canActivate:[AuthGuard]},
  {path: 'timeline-about', component: TimeAboutComponent,canActivate:[AuthGuard]},
  {path: 'timeline-friends', component: TimeFriendsComponent,canActivate:[AuthGuard]},
  {path: 'timeline-album', component: TimeAlbumComponent,canActivate:[AuthGuard]},
  {path:'about', component: AboutComponent},
  {path: '', component: MainPageComponent, canActivate:[AuthGuard]},
  {path: '**', component: MainPageComponent, canActivate:[AuthGuard]},

];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    SignUpComponent,
    ContactComponent,
    UserhomeComponent,
    LeftBarComponent,
    RightBarComponent,
    PublishComponent,
    CoolImagesComponent,
    MainPageComponent,
    TimeLinesComponent,
    TimeLineComponent,
    TimeAboutComponent,
    TimeAlbumComponent,
    TimeFriendsComponent,
    TimeLineProfileComponent,
    TimeLineDetailesComponent,
    LoginComponent,
    ProfileComponent,
    RequestsComponent,
    LoginComponent,
    AboutComponent,
  ],
    imports: [
        BrowserModule,
        HttpClientModule,
        RouterModule.forRoot(routes),
        FormsModule
    ],
  providers: [
    {
      provide:HTTP_INTERCEPTORS,
      useClass:InterceptorInterceptor,
      multi: true,
}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
