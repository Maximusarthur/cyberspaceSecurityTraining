import "../scss/app.scss"
import "../scss/index.scss"
import "../scss/business.scss"

export class AppComponent
{
    say()
    {
        console.log('hello');
    }
}

let app = new AppComponent();
app.say();