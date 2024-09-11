<template>
    <v-card outlined>
        <v-card-title>
            EditItem
        </v-card-title>

        <v-card-text>
            <String label="Contents" v-model="value.contents" :editMode="editMode"/>
            <String label="Title" v-model="value.title" :editMode="editMode"/>
            <Number label="Price" v-model="value.price" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="editItem"
            >
                EditItem
            </v-btn>
            
            <v-btn
                    color="primary"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'EditItemCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.contents = '';
            this.value.title = '';
            this.value.price = 0;
        },
        watch: {
        },
        methods: {
            editItem() {
                this.$emit('editItem', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

