

<template>
    <v-app id="inspire">
        <div>
            <v-app-bar color="primary" app clipped-left flat >
                
                <div style="display: flex; justify-content: center; align-items: center;">
    <img src="@/assets/logo.png" alt="PNG Image" key="" 
                    to="/" 
                    @click="goHome()"
                    style="margin-top:10px; font-size:20px; cursor:pointer;"/>
                    
                    <span style="font-weight: bold; font-size: 32px; margin-left: 10px; margin-top: 10px;">
                        꿈꾸는 서재
                    </span>
  </div>
                <v-spacer></v-spacer>
                <v-list color="primary" style="display: flex; flex-direction: row;">
                    <v-list-item
                        class="px-2"
                        key="bookPublishes"
                        to="/bookpublishes/bookPublishes"
                        @click="changeUrl()"
                        color="primary"
                        style="font-weight:700; "
                    >
                        내 작품
                    </v-list-item>


                    <v-list-item
                        class="px-2"
                        key="bookPurchases"
                        to="/bookpurchases/bookPurchases"
                        @click="changeUrl()"
                        color="primary"
                        style="font-weight:700; "
                    >
                        내 서재
                    </v-list-item>

                </v-list>
                

            </v-app-bar>

            
        </div>

        <v-main>
            <v-container style="padding:0px;" v-if="urlPath" fluid>
                <router-view></router-view>
            </v-container>
            <v-container style="padding:0px;" v-else fluid>
                <div>
                    홈입니다!
                </div>
                <!-- <div style="width:100%; margin:0px 0px 20px 0px; position: relative;">
                    <v-img style="width:100%; height:300px;"
                        src=""
                    ></v-img>
                    <div class="App-main-text-overlap"></div>
                    <div class="App-sub-text-overlap"></div>
                </div> -->
                <!-- <v-row>
                    <v-col cols="4" class="d-flex justify-center" v-for="(aggregate, index) in aggregate" :key="index">
                        <div 
                            class="flip-card"
                            @mouseover="flipCard(index)"
                            @mouseleave="unflipCard(index)"
                            :class="{ 'is-flipped': flipped[index] }"
                        >
                            <div class="flip-card-inner">
                                <div class="flip-card-front">
                                    <v-card
                                        class="mx-auto"
                                        style="width:300px; min-height: 310px; margin-bottom:20px; text-align: center; border-radius: 10px;"
                                        outlined
                                    >
                                        <v-list-item style="padding:15px; margin:0px;">
                                            <v-img style="width:100%; height:120px; border-radius: 10px;" :src="aggregate.ImageUrl"></v-img>
                                        </v-list-item>
                                        <div style="text-align: left; padding:10px 15px 15px 15px; margin-top:-10px;">
                                            <h2>{{ aggregate.title }}</h2>
                                            <div>{{ aggregate.description }}</div>
                                        </div>
                                    </v-card>
                                </div>
                                <div class="flip-card-back">
                                    <v-card
                                        color="primary"
                                        class="mx-auto"
                                        style="width:300px; min-height: 310px; margin-bottom:20px; text-align: center; border-radius: 10px;"
                                        outlined
                                        :key="aggregate.key"
                                        :to="aggregate.route"
                                        @click="changeUrl()"
                                    >
                                        <v-list-item style="padding:15px; margin:0px;">
                                            <v-img style="width:100%; height:120px; border-radius: 10px;" :src="aggregate.ImageUrl"></v-img>
                                        </v-list-item>
                                        <h2 style="color:white;">{{ aggregate.title }} 관리</h2>
                                    </v-card>
                                </div>
                            </div>
                        </div>
                    </v-col>
                </v-row> -->
            </v-container>
        </v-main>
    </v-app>
</template>

<script>

export default {
    name: "App",
    data: () => ({
        useComponent: "",
        drawer: true,
        components: [],
        sideBar: true,
        urlPath: null,
        flipped: [],
        ImageUrl: '',
        aggregate: [
            { 
                title: 'BookPublish', 
                description: '', 
                key: 'bookPublishes', 
                route: '/bookpublishes/bookPublishes',
                ImageUrl: '',
            },
            { 
                title: 'BookPurchase', 
                description: '', 
                key: 'bookPurchases', 
                route: '/bookpurchases/bookPurchases',
                ImageUrl: '',
            },
            { 
                title: 'Member', 
                description: '', 
                key: 'members', 
                route: '/members/members',
                ImageUrl: '',
            },
            { 
                title: 'BookReview', 
                description: '', 
                key: 'bookReviews', 
                route: '/reviews/bookReviews',
                ImageUrl: '',
            },
            { 
                title: 'GenStory', 
                description: '', 
                key: 'genStories', 
                route: '/genstories/genStories',
                ImageUrl: '',
            },
            { 
                title: 'GenImage', 
                description: '', 
                key: 'genImages', 
                route: '/genimages/genImages',
                ImageUrl: '',
            },
            { 
                title: 'Paymenthistory', 
                description: '', 
                key: 'paymenthistories', 
                route: '/pays/paymenthistories',
                ImageUrl: '',
            },
            
        ],
    }),
    
    async created() {
      var path = document.location.href.split("#/")
      this.urlPath = path[1];

    },
    watch: {
        cards(newCards) {
            this.flipped = new Array(newCards.length).fill(false);
        },
    },

    mounted() {
        var me = this;
        me.components = this.$ManagerLists;
    },

    methods: {
         
        changeUrl() {
            var path = document.location.href.split("#/")
            this.urlPath = path[1];
            this.flipped.fill(false);
        },
        goHome() {
            this.urlPath = null;
        },
        flipCard(index) {
            this.$set(this.flipped, index, true);
        },
        unflipCard(index) {
            this.$set(this.flipped, index, false);
        },
    }
};
</script>
<style>
</style>
