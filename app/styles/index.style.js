import { StyleSheet, Platform } from 'react-native';
const IS_IOS = Platform.OS === 'ios';

export const colors = {
    black: '#1a1917',
    gray: '#888888',
    background1: '#1a1917',
    background2: '#545351',
    white: '#ffffff'
};

export default StyleSheet.create({
    safeArea: {
        flex: 1,
        // edit this one for the safe area on iphone x
        marginTop: -50,
        backgroundColor: colors.black
    },
    container: {
        flex: 1,
        backgroundColor: colors.background1
    },
    gradient: {
        ...StyleSheet.absoluteFillObject
    },
    scrollview: {
        //flex: 1
    },
    map: {
        position: "absolute",
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
        zIndex: 0
        },
    exampleContainer: {
        paddingVertical: 30
    },
    exampleContainerDark: {
        backgroundColor: colors.black
    },
    exampleContainerLight: {
        backgroundColor: 'white'
    },
    title: {
        paddingHorizontal: 30,
        backgroundColor: 'transparent',
        color: 'rgba(255, 255, 255, 0.9)',
        fontSize: 20,
        fontWeight: 'bold',
        textAlign: 'left'
    },
    titleDark: {
        color: colors.black
    },
    subtitle: {
        marginTop: 5,
        paddingHorizontal: 30,
        backgroundColor: 'transparent',
        color: 'rgba(255, 255, 255, 0.75)',
        fontSize: 13,
        fontStyle: 'italic',
        textAlign: 'left'
    },
    subtitleDark: {
        marginTop: 5,
        paddingHorizontal: 30,
        backgroundColor: 'transparent',
        color: 'rgba(0, 0, 0, 0.75)',
        fontSize: 13,
        fontStyle: 'italic',
        textAlign: 'left'
    },
    slider: {
        marginTop: 15,
        overflow: 'visible' // for custom animations
    },
    sliderContentContainer: {
        paddingVertical: 10 // for custom animation
    },
    paginationContainer: {
        paddingVertical: 8
    },
    paginationDot: {
        width: 8,
        height: 8,
        borderRadius: 4,
        marginHorizontal: 8
    },
    indicator: {
        flex: 1,
        alignItems: 'center',
        justifyContent: 'center',
        height: 80
    },
    modal4: {
      },
      modal: {
        justifyContent: 'center',
        alignItems: 'center'
      },
      searchIconHeader: {
          color: '#fff', fontSize: 20,
          marginTop: IS_IOS ? 2 : 10
      },
      scrollViewBooks: {
          marginTop: 23
      }
});
