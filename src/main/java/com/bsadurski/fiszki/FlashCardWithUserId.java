package com.bsadurski.fiszki;

import javax.validation.constraints.NotEmpty;

// w przeciwienstwie do inych Klas (typu Encja) ta sie nie stala
// beenem (strzelam ze beenami staja sie Klasy zwracane z metod
//innych niektórych Beanów (Beanow jakiejs specyficznej adnotacji np Controller))
public class  FlashCardWithUserId extends FlashCard implements IGetUserId {

    @NotEmpty(message = "nie moze byc puste")
    String userId;

    public FlashCardWithUserId() {
        super();
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String id) {
        this.userId = id;
    }

}
