import React from "react";
import _ from "lodash";
import { Responsive, WidthProvider } from "react-grid-layout";
import RequestObject from "./RequestObject";
const ResponsiveReactGridLayout = WidthProvider(Responsive);

export default class DragFromOutsideLayout extends React.Component {
    static defaultProps = {
        className: "layout",
        rowHeight: 30,
        onLayoutChange: function() {},
        cols: { lg: 12, md: 10, sm: 6, xs: 4, xxs: 2 },
    };
    data=new Object();
    state = {
        currentBreakpoint: "lg",
        preventCollision: true,
        compactType: "horizontal",
        horizontalCompact: false,
        verticalCompact: false,
        mounted: false,
        layouts: { lg: generateLayout() }
    };

    componentDidMount() {
        this.setState({ mounted: true });
    }

    generateDOM() {
        return _.map(this.state.layouts.lg, function(l, i) {
            return (
                <div key={i} style={{backgroundColor:"gray"}}>
                    {1 ? (
                        <div >
                            <span className="text">{l.i}</span>
                        </div>
                    ) : (
                        <div>
                            <span className="text">{l.i}</span>
                        </div>
                    )}
                </div>
            );
        });
    }
    async getTablelist() {
        let obj = new RequestObject();
        await obj.getTableList(0, 0).then(()=> this.onNewLayout());
    }
    onBreakpointChange = breakpoint => {
        this.setState({
            currentBreakpoint: breakpoint
        });
    };

    onCompactTypeChange = () => {
        const { compactType: oldCompactType } = this.state;
        const compactType = " "
        this.setState({ compactType });
    };

    onLayoutChange = (layout, layouts) => {
        this.props.onLayoutChange(layout, layouts);
    };

    onNewLayout = () => {
        this.setState({
            layouts: { lg: generateLayout() }
        });
    };

    constructor(props, context) {
        super(props, context);
        this.getTablelist();
    }

    onDrop = (layout, layoutItem, _event) => {
        alert(`Dropped element props:\n${JSON.stringify(layoutItem, ['x', 'y', 'w', 'h'], 2)}`);
    };

    render() {
        return (
            <div>
                <div>
                    Current Breakpoint: {this.state.currentBreakpoint} (
                    {this.props.cols[this.state.currentBreakpoint]} columns)
                </div>
                <div>
                    Compaction type:{" "}
                    {_.capitalize(this.state.compactType) || "No Compaction"}
                </div>
                <button onClick={this.onNewLayout}>Generate New Layout</button>
                <button onClick={this.onCompactTypeChange}>
                    Change Compaction Type
                </button>
                <div
                    className="droppable-element"
                    draggable={true}
                    unselectable="on"
                    // this is a hack for firefox
                    // Firefox requires some kind of initialization
                    // which we can do by adding this attribute
                    // @see https://bugzilla.mozilla.org/show_bug.cgi?id=568313
                    onDragStart={e => e.dataTransfer.setData("text/plain", "")}
                >
                    Droppable Element (Drag me!)
                </div>
                <ResponsiveReactGridLayout
                    {...this.props}
                    layouts={this.state.layouts}
                    onBreakpointChange={this.onBreakpointChange}
                    onLayoutChange={this.onLayoutChange}
                    onDrop={this.onDrop}
                    // WidthProvider option
                    measureBeforeMount={false}
                    // I like to have it animate on mount. If you don't, delete `useCSSTransforms` (it's default `true`)
                    // and set `measureBeforeMount={true}`.
                    useCSSTransforms={this.state.mounted}
                    compactType={"horizontal"}
                    verticalCompact={false}
                    preventCollision={true}
                    isDroppable={true}
                >
                    {this.generateDOM()}
                </ResponsiveReactGridLayout>
            </div>
        );
    }
}

function generateLayout() {

    return _.map(RequestObject.data, function(item, i) {

        console.log("아이템 : "+item.positionX);
        return {
            x: i,
            y: i,
            w: item.sizeX,
            h: item.sizeY,
            i: item.tableId,
           // isConstruct: item.isConstruct
        };
    });
}

