

<template>
    <v-app id="inspire">
        <div>
            <v-app-bar color="primary" app clipped-left flat>
                <v-toolbar-title>
                    <span class="second-word font uppercase"
                        style="font-weight:700;"
                    >
                        <v-app-bar-nav-icon
                            @click="openSideBar()"
                            style="z-index:1;
                            height:56px;
                            width:30px;
                            margin-right:10px;
                            font-weight:300;
                            font-size:55px;"
                        >
                            <div style="line-height:100%;">≡</div>
                        </v-app-bar-nav-icon>
                    </span>
                </v-toolbar-title>
                <span v-if="urlPath!=null" 
                    class="mdi mdi-home" 
                    key="" 
                    to="/" 
                    @click="goHome()"
                    style="margin-left:10px; font-size:20px; cursor:pointer;"
		        ></span> 
                <v-spacer></v-spacer>

            </v-app-bar>

            <v-navigation-drawer app clipped flat v-model="sideBar">
                <v-list>
                    <v-list-item
                        class="px-2"
                        key="bookPublishes"
                        to="/bookpublishes/bookPublishes"
                        @click="changeUrl()"
                        color="primary"
                        style="font-weight:700;"
                    >
                        BookPublish
                    </v-list-item>


                    <v-list-item
                        class="px-2"
                        key="bookPurchases"
                        to="/bookpurchases/bookPurchases"
                        @click="changeUrl()"
                        color="primary"
                        style="font-weight:700;"
                    >
                        BookPurchase
                    </v-list-item>


                    <v-list-item
                        class="px-2"
                        key="members"
                        to="/members/members"
                        @click="changeUrl()"
                        color="primary"
                        style="font-weight:700;"
                    >
                        Member
                    </v-list-item>


                    <v-list-item
                        class="px-2"
                        key="bookReviews"
                        to="/reviews/bookReviews"
                        @click="changeUrl()"
                        color="primary"
                        style="font-weight:700;"
                    >
                        BookReview
                    </v-list-item>


                    <v-list-item
                        class="px-2"
                        key="genStories"
                        to="/genstories/genStories"
                        @click="changeUrl()"
                        color="primary"
                        style="font-weight:700;"
                    >
                        GenStory
                    </v-list-item>


                    <v-list-item
                        class="px-2"
                        key="genImages"
                        to="/genimages/genImages"
                        @click="changeUrl()"
                        color="primary"
                        style="font-weight:700;"
                    >
                        GenImage
                    </v-list-item>


                    <v-list-item
                        class="px-2"
                        key="paymenthistories"
                        to="/pays/paymenthistories"
                        @click="changeUrl()"
                        color="primary"
                        style="font-weight:700;"
                    >
                        Paymenthistory
                    </v-list-item>


                </v-list>
            </v-navigation-drawer>
        </div>

        <v-main>
            <v-container style="padding:0px;" v-if="urlPath" fluid>
                <router-view></router-view>
            </v-container>
            <v-container style="padding:0px;" v-else fluid>
                <div style="width:100%; margin:0px 0px 20px 0px; position: relative;">
                    <v-img style="width:100%; height:300px;"
                        src=""
                    ></v-img>
                    <div class="App-main-text-overlap"></div>
                    <div class="App-sub-text-overlap"></div>
                </div>
                <v-row>
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
                </v-row>
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
        openSideBar(){
            this.sideBar = !this.sideBar
        },
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
